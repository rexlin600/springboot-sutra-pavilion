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
 * Github Runner
 *
 * @author: hekunlin
 * @since: 2020/1/3
 */
@Slf4j
@Component
public class GithubRunner implements CommandLineRunner {

    @Value("${github.oauth.token}")
    private String token;

    /**
     * Github Client
     */
    public GitHubClient client;

    /**
     * service
     */
    public static DownloadService downloadService;
    public static RepositoryService repositoryService;
    public static UserService userService;

    /**
     * 程序启动之后处理的事情
     *
     * @param args
     * @throws Exception
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