package xyz.rexlin600.github.config.runner;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.DownloadService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Github runner
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class GithubRunner implements CommandLineRunner {

	/**
	 * downloadService
	 */
	public static DownloadService downloadService;
	/**
	 * repositoryService
	 */
	public static RepositoryService repositoryService;
	/**
	 * userService
	 */
	public static UserService userService;
	/**
	 * Client
	 */
	public GitHubClient client;
	/**
	 * Token
	 */
	@Value("${github.oauth.token}")
	private String token;

	/**
	 * Run *
	 *
	 * @param args args
	 * @throws Exception exception
	 */
	@Override
	public void run(String... args) throws Exception {
		if (StringUtils.isEmpty(token)) {
			log.error("==>  Oauth failed, Please check your token ...");
		}

		client = new GitHubClient();
		client.setOAuth2Token(token);

		// init service
		//collaboratorService = new CollaboratorService(client);
		//commitService = new CommitService(client);
		//contentsService = new ContentsService(client);
		//dataService = new DataService(client);
		//deployKeyService = new DeployKeyService(client);
		downloadService = new DownloadService(client);
		//eventService = new EventService(client);
		//gistService = new GistService(client);
		//issueService = new IssueService(client);
		//labelService = new LabelService(client);
		//markdownService = new MarkdownService(client);
		//milestoneService = new MilestoneService(client);
		//oAuthService = new OAuthService(client);
		//organizationService = new OrganizationService(client);
		//pullRequestService = new PullRequestService(client);
		//teamService = new TeamService(client);
		//watcherService = new WatcherService(client);

		userService = new UserService(client);
		repositoryService = new RepositoryService(client);

		log.info("==>  Oauth success ...");
	}

}