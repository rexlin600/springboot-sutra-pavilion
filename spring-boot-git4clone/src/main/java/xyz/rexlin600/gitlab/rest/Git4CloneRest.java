package xyz.rexlin600.gitlab.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.gitlab.biz.GitlabService;
import xyz.rexlin600.gitlab.common.apiparam.Response;
import xyz.rexlin600.gitlab.req.GitlabCloneReq;

/**
 * GitLab 批量下载小助手
 *
 * @author hekunlin
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Slf4j
@RestController
@RequestMapping("/gitlab")
public class Git4CloneRest {

	/**
	 * Gitlab service
	 */
	private GitlabService gitlabService;

	/**
	 * Git 4 clone rest
	 *
	 * @param gitlabService gitlab service
	 */
	@Autowired
	public Git4CloneRest(GitlabService gitlabService) {
		this.gitlabService = gitlabService;
	}

	/**
	 * 查询项目列表
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/list")
	public Response list(@RequestBody GitlabCloneReq req) {
		return gitlabService.list(req);
	}

	/**
	 * 批量下载项目
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/clone")
	public Response gitlab4Clone(@RequestBody GitlabCloneReq req) {
		return gitlabService.clone(req);
	}


}