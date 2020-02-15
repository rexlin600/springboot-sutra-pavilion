package xyz.rexlin600.biz.impl;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import xyz.rexlin600.biz.GitlabService;
import xyz.rexlin600.common.apiparam.Response;
import xyz.rexlin600.common.apiparam.ResponseGenerator;
import xyz.rexlin600.config.GitLabConfigBean;
import xyz.rexlin600.req.GitlabCloneReq;
import xyz.rexlin600.util.GitlabUtil;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * GitlabService 实现类
 *
 * @author: rexlin600
 * @date: 2020-02-15
 */
@Service
@Slf4j
public class GitlabServiceImpl implements GitlabService {

    /**
     * 线程池
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            100,
            120,
            15,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>());

    /**
     * GitLab 配置
     */
    @Autowired
    private GitLabConfigBean gitLabConfigBean;

    /**
     * 列表查询
     *
     * @param req
     * @return
     */
    @Override
    public Response list(GitlabCloneReq req) {
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitLabConfigBean.getHost(), gitLabConfigBean.getToken());
        List<GitlabProject> allProjects = gitlabAPI.getAllProjects();
        List<GitlabProject> matchList = getMatchGitlabProjects(req, allProjects);
        return ResponseGenerator.success(matchList);
    }

    /**
     * 克隆项目
     *
     * @param req
     * @return
     */
    @Override
    public Response clone(GitlabCloneReq req) {
        long start = Instant.now().toEpochMilli();

        // 建立 GitLab 连接、获取所有项目
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitLabConfigBean.getHost(), gitLabConfigBean.getToken());
        List<GitlabProject> allProjects = gitlabAPI.getAllProjects();
        log.info("==>  建立 GitLab【{}】连接，并获取所有的项目成功，共计【{}】个项目", gitLabConfigBean.getHost(), allProjects.size());

        // 筛选匹配名称的项目
        List<GitlabProject> matchList = getMatchGitlabProjects(req, allProjects);

        // 未找到匹配项目
        if (CollectionUtils.isEmpty(matchList)) {
            return ResponseGenerator.success("未找到匹配的项目");
        }

        // 输出地址匹配
        if (StringUtils.isEmpty(req.getDir())) {
            log.error("==>  未指定 clone 后存放的位置，请检查参数");
            return ResponseGenerator.fail("未指定克隆目录");
        }

        CountDownLatch countDownLatch = new CountDownLatch(matchList.size());

        // 组装 git 地址
        final UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(
                gitLabConfigBean.getUsername(), gitLabConfigBean.getPassword());
        final List<GitlabProject> finalMatchList = matchList;
        for (int i = 0; i < finalMatchList.size(); i++) {
            int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                GitlabProject m = finalMatchList.get(finalI);

                @Override
                public void run() {
                    try {
                        log.info("==>  clone 项目=【{}】到本地目录=【{}】】", m.getName(), req.getDir());
                        GitlabUtil.clone(req, provider, m);
                    } finally {
                        // 确保计数器 -1
                        countDownLatch.countDown();
                    }
                }
            });
        }

        // 等待线程执行结束再返回
        try {
            countDownLatch.await(gitLabConfigBean.getMaxWaitTime(), TimeUnit.SECONDS);
        } catch (Exception e) {
            log.info("<==  克隆项目已经等待=【{}】秒，请自己查看克隆是否完成", gitLabConfigBean.getMaxWaitTime());
            return ResponseGenerator.success("克隆超时，请自行检查是否克隆成功");
        }

        long end = Instant.now().toEpochMilli();

        log.info("<==  克隆结束，共计耗时=【{}】ms", (end - start));
        return ResponseGenerator.success("克隆结束");
    }

    /**
     * 筛选匹配的项目
     *
     * @param req
     * @param allProjects
     * @return
     */
    private List<GitlabProject> getMatchGitlabProjects(GitlabCloneReq req, List<GitlabProject> allProjects) {
        // 筛选匹配名称的项目
        List<GitlabProject> matchList = allProjects;
        if (!StringUtils.isEmpty(req.getName())) {
            log.info("==>  筛选模糊匹配 name=【{}】 的全部项目", req.getName());
            matchList = allProjects.stream()
                    .filter(m -> m.getName().contains(req.getName()))
                    .collect(Collectors.toList());
        }

        // 筛选匹配 owner 的项目
        if (!StringUtils.isEmpty(req.getOwner())) {
            log.info("==>  筛选指定 owner=【{}】 的全部项目", req.getOwner());
            matchList = matchList.stream().filter(m -> m.getOwner().equals(req.getOwner())).collect(Collectors.toList());
        }

        // 筛选指定 namespace 的项目
        if (!StringUtils.isEmpty(req.getNamespaceName())) {
            log.info("==>  筛选指定 namespaceName=【{}】 的全部项目", req.getNamespaceName());
            matchList = matchList.stream().filter(m -> m.getNamespace().getName().equals(req.getNamespaceName())).collect(Collectors.toList());
        }

        log.info("==>  满足筛选条件的共计【{}】个项目", matchList.size());

        return matchList;
    }


}