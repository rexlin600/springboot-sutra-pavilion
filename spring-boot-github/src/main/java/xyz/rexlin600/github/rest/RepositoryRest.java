package xyz.rexlin600.github.rest;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GsonUtils;
import org.eclipse.egit.github.core.client.PageIterator;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.github.common.apiparam.Response;
import xyz.rexlin600.github.common.apiparam.ResponseGenerator;
import xyz.rexlin600.github.common.util.StarredUtil;
import xyz.rexlin600.github.config.runner.GithubRunner;
import xyz.rexlin600.github.entity.StarredResp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Repository rest
 *
 * @author hekunlin
 */
@SuppressWarnings("ALL")
@Slf4j
@RestController
@RequestMapping(value = "/repository")
public class RepositoryRest {

	/**
	 * LINK
	 */
	public static final String LINK = "Link";

	/**
	 * Page response
	 *
	 * @param username username
	 * @param start    start
	 * @param end      end
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/page")
	public Response page(@RequestParam(value = "username", required = false) String username,
						 @RequestParam(value = "start", defaultValue = "1") Integer start,
						 @RequestParam(value = "end", defaultValue = "10") Integer end) {
		List<Repository> list = new ArrayList<>();
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		// 查询自己的仓库
		if (StringUtils.isEmpty(username)) {
			PageIterator<Repository> p1 = GithubRunner.repositoryService.pageRepositories(start, end);
			if (p1.hasNext()) {
				Collection<Repository> repositories = p1.next();
				list.addAll(repositories);
			}
		} else {    // 查询指定用户的仓库
			PageIterator<Repository> p2 = GithubRunner.repositoryService.pageRepositories(username, start, end);
			if (p2.hasNext()) {
				Collection<Repository> repositories = p2.next();
				list.addAll(repositories);
			}
		}
		return ResponseGenerator.success(list);
	}


	/**
	 * List response
	 *
	 * @param username username
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/list")
	public Response list(@RequestParam(value = "username") String username) {
		List<Repository> repositories = new ArrayList();
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		if (StringUtils.isEmpty(username)) {
			repositories = GithubRunner.repositoryService.getRepositories();
		} else {
			repositories = GithubRunner.repositoryService.getRepositories(username);
		}
		return ResponseGenerator.success(repositories);
	}


	/**
	 * Branches response
	 *
	 * @param searchRepository search repository
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/branches")
	public Response branches(@RequestBody SearchRepository searchRepository) {
		List<RepositoryBranch> branches =
				GithubRunner.repositoryService.getBranches(searchRepository);
		return ResponseGenerator.success(branches);
	}


	/**
	 * Tags response
	 *
	 * @param searchRepository search repository
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/tags")
	public Response tags(@RequestBody SearchRepository searchRepository) {
		List<RepositoryTag> tags = GithubRunner.repositoryService.getTags(searchRepository);
		return ResponseGenerator.success(tags);
	}


	/**
	 * Languages response
	 *
	 * @param searchRepository search repository
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/languages")
	public Response languages(@RequestBody SearchRepository searchRepository) {
		Map<String, Long> languages = GithubRunner.repositoryService.getLanguages(searchRepository);
		return ResponseGenerator.success(languages);
	}


	/**
	 * Contributors response
	 *
	 * @param flag             flag
	 * @param searchRepository search repository
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/contributors")
	public Response contributors(@RequestParam(value = "flag") boolean flag,
								 @RequestBody SearchRepository searchRepository) {
		List<Contributor> contributors = GithubRunner.repositoryService.getContributors(searchRepository, flag);
		return ResponseGenerator.success(contributors);
	}


	/**
	 * Forks response
	 *
	 * @param searchRepository search repository
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/forks")
	public Response forks(@RequestBody SearchRepository searchRepository) {
		List<Repository> forks = GithubRunner.repositoryService.getForks(searchRepository);
		return ResponseGenerator.success(forks);
	}


	/**
	 * Search response
	 *
	 * @param query     query
	 * @param language  language
	 * @param startPage start page
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/simple/search")
	public Response search(@RequestParam(value = "query") String query,
						   @RequestParam(value = "language", required = false) String language,
						   @RequestParam(value = "startPage", required = false) Integer startPage) {
		List<SearchRepository> list = new ArrayList();

		// resolve space
		if (!StringUtils.isEmpty(query)) {
			query = query.trim();
		}
		if (!StringUtils.isEmpty(language)) {
			language = language.trim();
		}

		// query is empty
		if (StringUtils.isEmpty(query.trim())) {
			return ResponseGenerator.success(list);
		}

		// language and startPage is empty
		if (StringUtils.isEmpty(language) && ObjectUtils.isEmpty(startPage)) {
			list = GithubRunner.repositoryService.searchRepositories(query);
		}

		// language is not empty and startpage is empty
		if (!StringUtils.isEmpty(language) && ObjectUtils.isEmpty(startPage)) {
			list = GithubRunner.repositoryService.searchRepositories(query, language);
		}

		// language is eempty and startPage is not eempty
		if (StringUtils.isEmpty(language) && !ObjectUtils.isEmpty(startPage)) {
			list = GithubRunner.repositoryService.searchRepositories(query, startPage);
		}

		// language is not empty and startPage is not empty
		if (StringUtils.isEmpty(language) && ObjectUtils.isEmpty(startPage)) {
			list = GithubRunner.repositoryService.searchRepositories(query, language, startPage);
		}

		return ResponseGenerator.success(list);
	}


	/**
	 * Create response
	 *
	 * @param repository repository
	 * @param org        org
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/create")
	public Response create(@RequestBody Repository repository,
						   @RequestParam(value = "org", required = false) String org) {
		Repository result = null;

		// resolve space
		if (!StringUtils.isEmpty(org)) {
			org = org.trim();
		}

		if (StringUtils.isEmpty(org)) {
			result = GithubRunner.repositoryService.createRepository(repository);
		} else {
			result = GithubRunner.repositoryService.createRepository(org, repository);
		}

		return ResponseGenerator.success(result);
	}


	/**
	 * Fork response
	 *
	 * @param org              org
	 * @param searchRepository search repository
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/fork")
	public Response fork(@RequestParam(value = "org", required = false) String org,
						 @RequestBody SearchRepository searchRepository) {
		Repository forkRepository = null;

		// resolve space
		if (!StringUtils.isEmpty(org)) {
			org = org.trim();
		}

		if (StringUtils.isEmpty(org)) {
			forkRepository = GithubRunner.repositoryService.forkRepository(searchRepository);
		} else {
			forkRepository = GithubRunner.repositoryService.forkRepository(searchRepository, org);
		}

		return ResponseGenerator.success(forkRepository);
	}

	/**
	 * Star list response
	 *
	 * @param page page
	 * @param user user
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/star/page")
	public Response<List<StarredResp>> starList(@RequestParam Integer page, @RequestParam String user) {
		Gson gson = GsonUtils.createGson(true);

		// concat req URL
		String starredUrl = StarredUtil.getGithuStarsbUrl(page, user);

		// merge list
		HttpResponse httpResponse = HttpUtil.createGet(starredUrl).execute();
		if (HttpStatus.HTTP_OK != httpResponse.getStatus()) {
			throw new RuntimeException("获取 Star 列表失败");
		}

		// TODO 获取头信息
		Integer count = 0;
		Map<String, List<String>> headers = httpResponse.headers();
		if (headers.containsKey(LINK)) {
			List<String> link = headers.get(LINK);
			log.info("link is: {}", link);
		}


		JsonArray jsonArray = gson.fromJson(httpResponse.body(), JsonArray.class);

		List<StarredResp> list = StarredUtil.convert(jsonArray);
		log.info("获取 Star 列表成功，数量：{}", list.size());

		return ResponseGenerator.success(list);
	}


}