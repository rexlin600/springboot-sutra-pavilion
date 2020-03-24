package xyz.rexlin600.github.rest;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.google.gson.*;
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
import xyz.rexlin600.github.common.constant.GithubConstant;
import xyz.rexlin600.github.common.util.StarredUtil;
import xyz.rexlin600.github.config.runner.GithubRunner;
import xyz.rexlin600.github.entity.StarredResp;

import java.util.*;

/**
 * @menu Github仓库API
 * @author: hekunlin
 * @date: 2020/1/3
 */
@SuppressWarnings("ALL")
@Slf4j
@RestController
@RequestMapping(value = "/repository")
public class RepositoryRest {

    /**
     * 1. 【分页-仓库列表】
     *
     * @param start 起始数量
     * @param end   截至数量
     * @return
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
     * 2. 【user-仓库列表】
     *
     * @param username
     * @return
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
     * 3. 【仓库branches-RepositoryId】
     *
     * @param searchRepository
     * @return
     */
    @SneakyThrows
    @PostMapping("/branches")
    public Response branches(@RequestBody SearchRepository searchRepository) {
        List<RepositoryBranch> branches =
                GithubRunner.repositoryService.getBranches(searchRepository);
        return ResponseGenerator.success(branches);
    }


    /**
     * 4. 【仓库tags】
     *
     * @param searchRepository
     * @return
     */
    @SneakyThrows
    @PostMapping("/tags")
    public Response tags(@RequestBody SearchRepository searchRepository) {
        List<RepositoryTag> tags = GithubRunner.repositoryService.getTags(searchRepository);
        return ResponseGenerator.success(tags);
    }


    /**
     * 5. 【仓库语言】
     *
     * @param searchRepository
     * @return
     */
    @SneakyThrows
    @PostMapping("/languages")
    public Response languages(@RequestBody SearchRepository searchRepository) {
        Map<String, Long> languages = GithubRunner.repositoryService.getLanguages(searchRepository);
        return ResponseGenerator.success(languages);
    }


    /**
     * 6. 【仓库贡献者】
     *
     * @param searchRepository
     * @return
     */
    @SneakyThrows
    @PostMapping("/contributors")
    public Response contributors(@RequestParam(value = "flag") boolean flag,
                                 @RequestBody SearchRepository searchRepository) {
        List<Contributor> contributors = GithubRunner.repositoryService.getContributors(searchRepository, flag);
        return ResponseGenerator.success(contributors);
    }


    /**
     * 7. 【fork列表】
     *
     * @param searchRepository
     * @return
     */
    @SneakyThrows
    @PostMapping("/forks")
    public Response forks(@RequestBody SearchRepository searchRepository) {
        List<Repository> forks = GithubRunner.repositoryService.getForks(searchRepository);
        return ResponseGenerator.success(forks);
    }


    /**
     * 8. 【简单-根据条件筛选仓库】
     *
     * @param query
     * @param language
     * @param startPage
     * @return
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
     * 9. 【创建仓库】
     *
     * @param repository
     * @param org
     * @return
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
     * 10. 【fork仓库】
     *
     * @param org
     * @param searchRepository
     * @return
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
     * 11. 【获取某个用户的 Star，分页查询】
     *
     * @param page 第几页
     * @param user 用户
     * @return
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
        if (headers.containsKey("Link")) {
            List<String> link = headers.get("Link");
            log.info("link is: {}", link);
        }


        JsonArray jsonArray = gson.fromJson(httpResponse.body(), JsonArray.class);

        List<StarredResp> list = StarredUtil.convert(jsonArray);
        log.info("获取 Star 列表成功，数量：{}", list.size());

        return ResponseGenerator.success(list);
    }


}