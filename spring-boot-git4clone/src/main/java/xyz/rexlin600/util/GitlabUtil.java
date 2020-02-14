package xyz.rexlin600.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab.api.models.GitlabProject;
import org.springframework.stereotype.Component;
import xyz.rexlin600.req.GitlabCloneReq;

import java.io.File;

/**
 * GitlabUtil 工具类
 *
 * @author: rexlin600
 * @date: 2020-02-15
 */
@Slf4j
@Component
public class GitlabUtil {

    /**
     * 克隆项目
     *
     * @param req
     * @param provider
     * @param project
     */
    public static void clone(GitlabCloneReq req, UsernamePasswordCredentialsProvider provider, GitlabProject project) throws GitAPIException {
        CloneCommand cloneCommand = Git.cloneRepository();
        Git git = cloneCommand
                .setURI(project.getHttpUrl())
                .setProgressMonitor(new ProgressMonitor() {
                    @Override
                    public void start(int i) {
                        log.info("********** clone 项目=【{}】到本地目录=【{}】】，状态：进行中 **********", project.getName(), req.getOutDir());
                    }

                    @Override
                    public void beginTask(String s, int i) {
                        // 不作处理
                    }

                    @Override
                    public void update(int i) {
                        // 不作处理
                    }

                    @Override
                    public void endTask() {
                        //log.info("********** 项目=【{}】 clone 状态：已完成 **********", project.getName());
                    }

                    @Override
                    public boolean isCancelled() {
                        return false;
                    }
                })
                .setDirectory(new File(req.getOutDir() + "/" + project.getNameWithNamespace()))
                .setCredentialsProvider(provider)
                .call();
    }

}