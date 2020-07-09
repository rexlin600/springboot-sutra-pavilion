package xyz.rexlin600.gitlab.biz.impl;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import xyz.rexlin600.gitlab.biz.GitlabService;
import xyz.rexlin600.gitlab.common.apiparam.Response;
import xyz.rexlin600.gitlab.common.apiparam.ResponseGenerator;
import xyz.rexlin600.gitlab.config.GitLabConfigBean;
import xyz.rexlin600.gitlab.req.GitlabCloneReq;
import xyz.rexlin600.gitlab.util.GitlabUtil;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * GitlabService 实现类
 *
 * @author: rexlin600
 * @since: 2020-02-15
 */
@Service
@Slf4j
public class GitlabServiceImpl implements GitlabService {

    /**
     * 线程池
     */
    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("gitlab4clone-pool-%d").build();
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            5,
            10,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(),
            namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());

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
        GitlabAPI gitlabApi = GitlabAPI.connect(gitLabConfigBean.getHost(), gitLabConfigBean.getToken());
        List<GitlabProject> allProjects = gitlabApi.getAllProjects();
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
        GitlabAPI gitlabApi = GitlabAPI.connect(gitLabConfigBean.getHost(), gitLabConfigBean.getToken());
        List<GitlabProject> allProjects = gitlabApi.getAllProjects();
        log.info("==>  建立 GitLab[{}]连接，并获取所有的项目成功，共计[{}]个项目", gitLabConfigBean.getHost(), allProjects.size());

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
                    log.info("==>  clone 第[{}]个项目=[{}]到本地目录=[{}]]", (finalI + 1), m.getName(), req.getDir());
                    GitlabUtil.clone(req, provider, m, countDownLatch);
                }
            });
        }

        // 等待线程执行结束再返回
        try {
            boolean await = countDownLatch.await(Long.valueOf(gitLabConfigBean.getMaxTime().longValue()), TimeUnit.SECONDS);
            if (!await) {
                log.info("<==  克隆项目已经等待=[{}]秒，请自己核查克隆是否完成", gitLabConfigBean.getMaxTime());
                return ResponseGenerator.success("克隆超时，后续克隆将继续进行");
            }
        } catch (Exception e) {
            log.error("==>  克隆等待出现异常=[{}]", e.getMessage());
            return ResponseGenerator.fail("克隆失败");
        }

        long end = Instant.now().toEpochMilli();

        log.info("<==  克隆结束，全流程共计耗时=[{}]ms", (end - start));
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
            log.info("==>  筛选模糊匹配 name=[{}] 的全部项目", req.getName());
            matchList = allProjects.stream()
                    .filter(m -> m.getName().contains(req.getName()))
                    .collect(Collectors.toList());
        }

        // 筛选匹配 owner 的项目
        if (!StringUtils.isEmpty(req.getOwner())) {
            log.info("==>  筛选指定 owner=[{}] 的全部项目", req.getOwner());
            matchList = matchList.stream().filter(m -> m.getOwner().equals(req.getOwner())).collect(Collectors.toList());
        }

        // 筛选指定 namespace 的项目
        if (!StringUtils.isEmpty(req.getNamespaceName())) {
            log.info("==>  筛选指定 namespaceName=[{}] 的全部项目", req.getNamespaceName());
            matchList = matchList.stream().filter(m -> m.getNamespace().getName().equals(req.getNamespaceName())).collect(Collectors.toList());
        }

        log.info("==>  满足筛选条件的共计[{}]个项目", matchList.size());

        return matchList;
    }


}