package xyz.rexlin600.gitlab.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.BatchingProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab.api.models.GitlabProject;
import org.springframework.stereotype.Component;
import xyz.rexlin600.gitlab.req.GitlabCloneReq;

import java.io.File;
import java.util.concurrent.CountDownLatch;

/**
 * Gitlab util
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class GitlabUtil {

	/**
	 * CHECKOUT_FILE
	 */
	private final static String CHECKOUT_FILE = "Checking out files";
	/**
	 * SUCCESS
	 */
	private final static Integer SUCCESS = 100;

	/**
	 * Clone *
	 *
	 * @param req            req
	 * @param provider       provider
	 * @param project        project
	 * @param countDownLatch count down latch
	 */
	public static void clone(GitlabCloneReq req, UsernamePasswordCredentialsProvider provider, GitlabProject project, CountDownLatch countDownLatch) {
		CloneCommand cloneCommand = Git.cloneRepository();
		try {
			cloneCommand
					.setURI(project.getHttpUrl())
					.setProgressMonitor(new BatchingProgressMonitor() {
						@Override
						protected void onUpdate(String s, int i) {
							// doNothing
						}

						@Override
						protected void onEndTask(String s, int i) {
							// doNothing
						}

						@Override
						protected void onUpdate(String s, int i, int i1, int i2) {
							// doNothing
						}

						@Override
						protected void onEndTask(String s, int i, int i1, int i2) {
							if (s.equals(CHECKOUT_FILE) && SUCCESS.equals(i2)) {
								log.info("<==  克隆项目=[{}]完成, taskName=[{}], cmp=[{}], totalWork=[{}], pcnt=[{}]", project.getName(), s, i, i1, i2);
								countDownLatch.countDown();
							}
						}
					})
					.setDirectory(new File(req.getDir() + "/" + project.getNameWithNamespace()))
					.setCredentialsProvider(provider)
					.call();
		} catch (Exception ex) {
			// 克隆失败则打印错误日志、计数器-1
			log.error("<==  克隆项目=[{}]失败，原因=[{}]", project.getName(), ex.getMessage());
			countDownLatch.countDown();
		}
	}

}