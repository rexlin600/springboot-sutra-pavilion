package github;

import lombok.SneakyThrows;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

/**
 * @author: hekunlin
 * @date: 2020/1/3
 */
public class GithubTests {

    @SneakyThrows
    public static void main(String[] args) {
        //Basic authentication
        GitHubClient client = new GitHubClient();
        client.setCredentials("rexlin600", "950401hkl");

        // get user name
        String user = client.getUser();

        // get Repositories
        RepositoryService service = new RepositoryService();
        for (Repository repo : service.getRepositories(user)) {
            System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
        }

        //OAuth2 token authentication
        //client.setOAuth2Token("b7069a1f5f33eb97b5330f910062730af85307a4");
    }

}