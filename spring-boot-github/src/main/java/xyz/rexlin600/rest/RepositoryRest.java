package xyz.rexlin600.rest;

import lombok.SneakyThrows;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.common.apiparam.Response;
import xyz.rexlin600.common.apiparam.ResponseGenerator;
import xyz.rexlin600.config.runner.GRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @menu Github仓库API
 * @author: hekunlin
 * @date: 2020/1/3
 */
@RestController
@RequestMapping(value = "/github")
public class RepositoryRest {

    /**
     * 1. 【分页查询】
     *
     * @param start 起始数量
     * @param end   截至数量
     * @return
     */
    @SneakyThrows
    @GetMapping("/repositories")
    public Response repositories(@RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "start", defaultValue = "1") Integer start,
                                 @RequestParam(value = "end", defaultValue = "10") Integer end) {
        RepositoryService repositoryService = new RepositoryService(GRunner.client);
        int size = end - start + 1;
        List<Repository> list = new ArrayList<>(size);

        if (StringUtils.isEmpty(username)) {    // 查询自己的仓库
            PageIterator<Repository> p1 = repositoryService.pageRepositories(start, end);
            while (p1.hasNext()) {
                Collection<Repository> repositories = p1.next();
                list = repositories.parallelStream().collect(Collectors.toList());
            }
        } else {    // 查询指定用户的仓库
            PageIterator<Repository> p2 = repositoryService.pageRepositories(username, start, end);
            while (p2.hasNext()) {
                Collection<Repository> repositories = p2.next();
                list = repositories.parallelStream().collect(Collectors.toList());
            }
        }

        return ResponseGenerator.success(list);
    }

}