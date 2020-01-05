package xyz.rexlin600.rest;

import lombok.SneakyThrows;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.PageIterator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.common.apiparam.Response;
import xyz.rexlin600.common.apiparam.ResponseGenerator;
import xyz.rexlin600.config.runner.GRunner;

import java.util.*;

/**
 * @menu Github仓库API
 * @author: hekunlin
 * @date: 2020/1/3
 */
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
    @GetMapping("/page/repositories")
    public Response pageRepositories(@RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "start", defaultValue = "1") Integer start,
                                     @RequestParam(value = "end", defaultValue = "10") Integer end) {
        int size = end - start + 1;
        List<Repository> list = new ArrayList<>(size);
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        if (StringUtils.isEmpty(username)) {    // 查询自己的仓库
            PageIterator<Repository> p1 = GRunner.repositoryService.pageRepositories(start, end);
            while (p1.hasNext()) {
                Collection<Repository> repositories = p1.next();
                list.addAll(repositories);
            }
        } else {    // 查询指定用户的仓库
            PageIterator<Repository> p2 = GRunner.repositoryService.pageRepositories(username, start, end);
            while (p2.hasNext()) {
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
    @GetMapping("/repositories")
    public Response repositories(@RequestParam(value = "username") String username) {
        List<Repository> repositories = Collections.emptyList();
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        if (StringUtils.isEmpty(username)) {
            repositories = GRunner.repositoryService.getRepositories();
        } else {
            repositories = GRunner.repositoryService.getRepositories(username);
        }
        return ResponseGenerator.success(repositories);
    }


    /**
     * 3. 【Map-仓库列表】
     *
     * @param filterData
     * @return
     */
    @SneakyThrows
    @PostMapping("/filter/repositories")
    public Response filterRepositories(@RequestBody Map<String, String> filterData) {
        List<Repository> list = GRunner.repositoryService.getRepositories(filterData);
        return ResponseGenerator.success(list);
    }


    /**
     * 4. 【仓库branches】
     *
     * @param repository
     * @return
     */
    @SneakyThrows
    @PostMapping("/branches")
    public Response branches(@RequestBody IRepositoryIdProvider repository) {
        List<RepositoryBranch> branches =
                GRunner.repositoryService.getBranches(repository);
        return ResponseGenerator.success(branches);
    }


    /**
     * 5. 【仓库tags】
     *
     * @param repository
     * @return
     */
    @SneakyThrows
    @PostMapping("/tags")
    public Response tags(@RequestBody IRepositoryIdProvider repository) {
        List<RepositoryTag> tags = GRunner.repositoryService.getTags(repository);
        return ResponseGenerator.success(tags);
    }


    /**
     * 6. 【仓库语言】
     *
     * @param repository
     * @return
     */
    @SneakyThrows
    @PostMapping("/languages")
    public Response languages(@RequestBody IRepositoryIdProvider repository) {
        Map<String, Long> languages = GRunner.repositoryService.getLanguages(repository);
        return ResponseGenerator.success(languages);
    }


    /**
     * 6. 【仓库共享者】
     *
     * @param repository
     * @return
     */
    @SneakyThrows
    @PostMapping("/contributors")
    public Response contributors(@RequestBody IRepositoryIdProvider repository,
                                 @RequestParam(value = "是否显示匿名用户") boolean flag) {
        List<Contributor> contributors = GRunner.repositoryService.getContributors(repository, flag);
        return ResponseGenerator.success(contributors);
    }


    /**
     * 7. 【fork列表】
     *
     * @param repository
     * @return
     */
    @SneakyThrows
    @PostMapping("/forks")
    public Response forks(@RequestBody IRepositoryIdProvider repository) {
        List<Repository> forks = GRunner.repositoryService.getForks(repository);
        return ResponseGenerator.success(forks);
    }


    /**
     * 8. 【hook值/列表】
     *
     * @param repository
     * @param hookId
     * @return
     */
    @SneakyThrows
    @PostMapping("/hooks")
    public Response hooks(@RequestBody IRepositoryIdProvider repository,
                          @RequestParam(value = "hookId", required = false) Integer hookId) {
        List<RepositoryHook> hooks = Collections.emptyList();
        if (ObjectUtils.isEmpty(hookId)) {
            hooks = GRunner.repositoryService.getHooks(repository);
        } else {
            RepositoryHook hook = GRunner.repositoryService.getHook(repository, hookId);
            return ResponseGenerator.success(hook);
        }
        return ResponseGenerator.success(hooks);
    }


    /**
     * 9. 【某组织的仓库】
     *
     * @param organization
     * @param filterData
     * @return
     */
    @SneakyThrows
    @PostMapping("/orgRepositories")
    public Response orgRepositories(@RequestParam(value = "organization", required = false) String organization,
                                    @RequestBody Map<String, String> filterData) {
        List<Repository> list = Collections.emptyList();
        // resolve space
        if (!StringUtils.isEmpty(organization)) {
            organization = organization.trim();
        }

        if (CollectionUtils.isEmpty(filterData)) {
            list = GRunner.repositoryService.getOrgRepositories(organization);
        } else {
            list = GRunner.repositoryService.getOrgRepositories(organization, filterData);
        }
        return ResponseGenerator.success(list);
    }


    /**
     * 10. 【简单-根据条件筛选仓库】
     *
     * @param query
     * @param language
     * @param startPage
     * @return
     */
    @SneakyThrows
    @PostMapping("/simple/searchRepository")
    public Response searchRepository(@RequestParam(value = "query") String query,
                                     @RequestParam(value = "language", required = false) String language,
                                     @RequestParam(value = "startPage", required = false) Integer startPage) {
        List<SearchRepository> list = Collections.emptyList();

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
            list = GRunner.repositoryService.searchRepositories(query);
        }

        // language is not empty and startpage is empty
        if (!StringUtils.isEmpty(language) && ObjectUtils.isEmpty(startPage)) {
            list = GRunner.repositoryService.searchRepositories(query, language);
        }

        // language is eempty and startPage is not eempty
        if (StringUtils.isEmpty(language) && !ObjectUtils.isEmpty(startPage)) {
            list = GRunner.repositoryService.searchRepositories(query, startPage);
        }

        // language is not empty and startPage is not empty
        if (StringUtils.isEmpty(language) && ObjectUtils.isEmpty(startPage)) {
            list = GRunner.repositoryService.searchRepositories(query, language, startPage);
        }

        return ResponseGenerator.success(list);
    }


    /**
     * 11. 【自由-根据条件筛选仓库】
     *
     * @param queryParams
     * @param startPage
     * @return
     */
    @SneakyThrows
    @PostMapping("/free/searchRepository")
    public Response searchRepository(@RequestBody Map<String, String> queryParams,
                                     @RequestParam(value = "startPage", required = false) Integer startPage) {
        List<SearchRepository> list = Collections.emptyList();

        if (ObjectUtils.isEmpty(startPage)) {
            list = GRunner.repositoryService.searchRepositories(queryParams);
        } else {
            list = GRunner.repositoryService.searchRepositories(queryParams, startPage);
        }

        return ResponseGenerator.success(list);
    }


    /**
     * 12. 【创建仓库】
     *
     * @param repository
     * @param organization
     * @return
     */
    @SneakyThrows
    @PostMapping("/createRepository")
    public Response createRepository(@RequestBody Repository repository,
                                     @RequestParam(value = "organization", required = false) String organization) {
        Repository result = null;

        // resolve space
        if (!StringUtils.isEmpty(organization)) {
            organization = organization.trim();
        }

        if (StringUtils.isEmpty(organization)) {
            result = GRunner.repositoryService.createRepository(repository);
        } else {
            result = GRunner.repositoryService.createRepository(organization, repository);
        }

        return ResponseGenerator.success(result);
    }


    /**
     * 13. 【创建Hook】
     *
     * @param repository
     * @param hook
     * @return
     */
    @SneakyThrows
    @PostMapping("/createHook")
    public Response createHook(@RequestBody IRepositoryIdProvider repository,
                               @RequestBody RepositoryHook hook) {
        RepositoryHook repositoryHook = GRunner.repositoryService.createHook(repository, hook);
        return ResponseGenerator.success(repositoryHook);
    }


    /**
     * 14. 【删除Hook】
     *
     * @param repository
     * @param hookId
     * @return
     */
    @SneakyThrows
    @PostMapping("/deleteHook")
    public Response deleteHook(@RequestBody IRepositoryIdProvider repository,
                               @RequestParam(value = "hookId") Integer hookId) {
        GRunner.repositoryService.deleteHook(repository, hookId);
        return ResponseGenerator.success();
    }


    /**
     * 15. 【编辑hook】
     *
     * @param repository
     * @param hook
     * @return
     */
    @SneakyThrows
    @PostMapping("/editHook")
    public Response editHook(@RequestBody IRepositoryIdProvider repository,
                             @RequestBody RepositoryHook hook) {
        RepositoryHook repositoryHook = GRunner.repositoryService.editHook(repository, hook);
        return ResponseGenerator.success(repositoryHook);
    }


    /**
     * 16. 【获取hook】
     *
     * @param repository
     * @param hookId
     * @return
     */
    @SneakyThrows
    @PostMapping("/getHook")
    public Response getHook(@RequestBody IRepositoryIdProvider repository,
                            @RequestParam(value = "hookId") Integer hookId) {
        RepositoryHook repositoryHook = GRunner.repositoryService.getHook(repository, hookId);
        return ResponseGenerator.success(repositoryHook);
    }


    /**
     * 17. 【测试hook】
     *
     * @param repository
     * @param hookId
     * @return
     */
    @SneakyThrows
    @PostMapping("/testHook")
    public Response testHook(@RequestBody IRepositoryIdProvider repository,
                             @RequestParam(value = "hookId") Integer hookId) {
        GRunner.repositoryService.testHook(repository, hookId);
        return ResponseGenerator.success();
    }


    /**
     * 18. 【fork仓库】
     *
     * @param organization
     * @param repository
     * @return
     */
    @SneakyThrows
    @PostMapping("/forkRepository")
    public Response forkRepository(@RequestParam(value = "organization") String organization,
                                   @RequestBody IRepositoryIdProvider repository) {
        Repository forkRepository = null;

        // resolve space
        if (!StringUtils.isEmpty(organization)) {
            organization = organization.trim();
        }

        if (StringUtils.isEmpty(organization)) {
            forkRepository = GRunner.repositoryService.forkRepository(repository);
        } else {
            forkRepository = GRunner.repositoryService.forkRepository(repository, organization);
        }

        return ResponseGenerator.success(forkRepository);
    }


}