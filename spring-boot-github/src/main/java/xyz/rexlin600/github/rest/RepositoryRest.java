package xyz.rexlin600.github.rest;

import lombok.SneakyThrows;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.PageIterator;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.github.common.apiparam.Response;
import xyz.rexlin600.github.common.apiparam.ResponseGenerator;
import xyz.rexlin600.github.config.runner.GithubRunner;

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
            while (p1.hasNext()) {
                Collection<Repository> repositories = p1.next();
                list.addAll(repositories);
                break;
            }
        } else {    // 查询指定用户的仓库
            PageIterator<Repository> p2 = GithubRunner.repositoryService.pageRepositories(username, start, end);
            while (p2.hasNext()) {
                Collection<Repository> repositories = p2.next();
                list.addAll(repositories);
                break;
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
     * @param organization
     * @return
     */
    @SneakyThrows
    @PostMapping("/create")
    public Response create(@RequestBody Repository repository,
                           @RequestParam(value = "organization", required = false) String organization) {
        Repository result = null;

        // resolve space
        if (!StringUtils.isEmpty(organization)) {
            organization = organization.trim();
        }

        if (StringUtils.isEmpty(organization)) {
            result = GithubRunner.repositoryService.createRepository(repository);
        } else {
            result = GithubRunner.repositoryService.createRepository(organization, repository);
        }

        return ResponseGenerator.success(result);
    }


    /**
     * 10. 【fork仓库】
     *
     * @param organization
     * @param searchRepository
     * @return
     */
    @SneakyThrows
    @PostMapping("/fork")
    public Response fork(@RequestParam(value = "organization", required = false) String organization,
                         @RequestBody SearchRepository searchRepository) {
        Repository forkRepository = null;

        // resolve space
        if (!StringUtils.isEmpty(organization)) {
            organization = organization.trim();
        }

        if (StringUtils.isEmpty(organization)) {
            forkRepository = GithubRunner.repositoryService.forkRepository(searchRepository);
        } else {
            forkRepository = GithubRunner.repositoryService.forkRepository(searchRepository, organization);
        }

        return ResponseGenerator.success(forkRepository);
    }


    // -----------------------------------------------------------------------------------------------
    // DEPRECATED METHOD
    // -----------------------------------------------------------------------------------------------

    ///**
    // * 11. 【自由-根据条件筛选仓库】
    // *
    // * @param queryParams
    // * @param startPage
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/free/search")
    //public Response search(@RequestBody Map<String, String> queryParams,
    //                                 @RequestParam(value = "startPage", required = false) Integer startPage) {
    //    List<SearchRepository> list = new ArrayList();
    //
    //    if (ObjectUtils.isEmpty(startPage)) {
    //        list = GRunner.repositoryService.searchRepositories(queryParams);
    //    } else {
    //        list = GRunner.repositoryService.searchRepositories(queryParams, startPage);
    //    }
    //
    //    return ResponseGenerator.success(list);
    //}
    //
    //
    ///**
    // * 12. 【hook值/列表】
    // *
    // * @param searchRepository
    // * @param hookId
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/hooks")
    //public Response hooks(@RequestBody SearchRepository searchRepository,
    //                      @RequestParam(value = "hookId", required = false) Integer hookId) {
    //    List<RepositoryHook> hooks = new ArrayList();
    //    if (ObjectUtils.isEmpty(hookId)) {
    //        hooks = GRunner.repositoryService.getHooks(searchRepository);
    //    } else {
    //        RepositoryHook hook = GRunner.repositoryService.getHook(searchRepository, hookId);
    //        return ResponseGenerator.success(hook);
    //    }
    //    return ResponseGenerator.success(hooks);
    //}
    //
    //
    ///**
    // * 13. 【某组织的仓库】
    // *
    // * @param organization
    // * @param filterData
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/org")
    //public Response org(@RequestParam(value = "organization", required = false) String organization,
    //                                @RequestBody Map<String, String> filterData) {
    //    List<Repository> list = new ArrayList();
    //    // resolve space
    //    if (!StringUtils.isEmpty(organization)) {
    //        organization = organization.trim();
    //    }
    //
    //    if (CollectionUtils.isEmpty(filterData)) {
    //        list = GRunner.repositoryService.getOrgRepositories(organization);
    //    } else {
    //        list = GRunner.repositoryService.getOrgRepositories(organization, filterData);
    //    }
    //    return ResponseGenerator.success(list);
    //}
    //
    ///**
    // * 14. 【Map-仓库列表】
    // *
    // * @param filterData
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/filter")
    //public Response filter(@RequestBody Map<String, String> filterData) {
    //    List<Repository> list = GRunner.repositoryService.getRepositories(filterData);
    //    return ResponseGenerator.success(list);
    //}
    //
    ///**
    // * 15. 【创建Hook】
    // *
    // * @param searchRepository
    // * @param hook
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/createHook")
    //public Response createHook(@RequestBody SearchRepository searchRepository,
    //                           @RequestBody RepositoryHook hook) {
    //    RepositoryHook repositoryHook = GRunner.repositoryService.createHook(searchRepository, hook);
    //    return ResponseGenerator.success(repositoryHook);
    //}
    //
    //
    ///**
    // * 16. 【删除Hook】
    // *
    // * @param searchRepository
    // * @param hookId
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/deleteHook")
    //public Response deleteHook(@RequestBody SearchRepository searchRepository,
    //                           @RequestParam(value = "hookId") Integer hookId) {
    //    GRunner.repositoryService.deleteHook(searchRepository, hookId);
    //    return ResponseGenerator.success();
    //}
    //
    //
    ///**
    // * 17. 【编辑hook】
    // *
    // * @param searchRepository
    // * @param hook
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/editHook")
    //public Response editHook(@RequestBody SearchRepository searchRepository,
    //                         @RequestBody RepositoryHook hook) {
    //    RepositoryHook repositoryHook = GRunner.repositoryService.editHook(searchRepository, hook);
    //    return ResponseGenerator.success(repositoryHook);
    //}
    //
    //
    ///**
    // * 18. 【获取hook】
    // *
    // * @param searchRepository
    // * @param hookId
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/getHook")
    //public Response getHook(@RequestBody SearchRepository searchRepository,
    //                        @RequestParam(value = "hookId") Integer hookId) {
    //    RepositoryHook repositoryHook = GRunner.repositoryService.getHook(searchRepository, hookId);
    //    return ResponseGenerator.success(repositoryHook);
    //}
    //
    //
    ///**
    // * 19. 【测试hook】
    // *
    // * @param searchRepository
    // * @param hookId
    // * @return
    // */
    //@Deprecated
    //@SneakyThrows
    //@PostMapping("/testHook")
    //public Response testHook(@RequestBody SearchRepository searchRepository,
    //                         @RequestParam(value = "hookId") Integer hookId) {
    //    GRunner.repositoryService.testHook(searchRepository, hookId);
    //    return ResponseGenerator.success();
    //}


}