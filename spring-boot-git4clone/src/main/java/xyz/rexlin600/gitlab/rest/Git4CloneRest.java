package xyz.rexlin600.gitlab.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.gitlab.biz.GitlabService;
import xyz.rexlin600.gitlab.common.apiparam.Response;
import xyz.rexlin600.gitlab.req.GitlabCloneReq;

/**
 * @menu Gitlab克隆小助手
 * @author: rexlin600
 * @date: 2020-02-14
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Slf4j
@RestController
@RequestMapping("/gitlab")
public class Git4CloneRest {

    private GitlabService gitlabService;

    @Autowired
    public Git4CloneRest(GitlabService gitlabService) {
        this.gitlabService = gitlabService;
    }

    /**
     * 1. 【查询所有项目】
     *
     * @return
     */
    @PostMapping("/list")
    public Response list(@RequestBody GitlabCloneReq req) {
        return gitlabService.list(req);
    }

    /**
     * 2. 【克隆项目】
     *
     * @param req
     * @return
     */
    @PostMapping("/clone")
    public Response gitlab4Clone(@RequestBody GitlabCloneReq req) {
        return gitlabService.clone(req);
    }


}