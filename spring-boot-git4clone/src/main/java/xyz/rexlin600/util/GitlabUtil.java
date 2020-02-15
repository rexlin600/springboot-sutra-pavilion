package xyz.rexlin600.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
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
    public static void clone(GitlabCloneReq req, UsernamePasswordCredentialsProvider provider, GitlabProject project) {
        CloneCommand cloneCommand = Git.cloneRepository();
        try {
            Git git = cloneCommand
                    .setURI(project.getHttpUrl())
                    .setDirectory(new File(req.getDir() + "/" + project.getNameWithNamespace()))
                    .setCredentialsProvider(provider)
                    .call();
        } catch (Exception ex) {
            // 克隆失败则打印错误日志即可
            log.error("<==  克隆项目=【{}】失败，原因=【{}】", project.getName(), ex.getMessage());
        }
    }

}