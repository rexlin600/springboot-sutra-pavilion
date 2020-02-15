package xyz.rexlin600.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
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
                .setDirectory(new File(req.getOutDir() + "/" + project.getNameWithNamespace()))
                .setCredentialsProvider(provider)
                .call();
    }

}