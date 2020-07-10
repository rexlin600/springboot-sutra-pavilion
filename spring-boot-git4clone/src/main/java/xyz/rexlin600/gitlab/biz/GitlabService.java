package xyz.rexlin600.gitlab.biz;

import xyz.rexlin600.gitlab.common.apiparam.Response;
import xyz.rexlin600.gitlab.req.GitlabCloneReq;

/**
 * Gitlab service
 *
 * @author hekunlin
 */
public interface GitlabService {

	/**
	 * List response
	 *
	 * @param req req
	 * @return the response
	 */
	Response list(GitlabCloneReq req);

	/**
	 * Clone response
	 *
	 * @param req req
	 * @return the response
	 */
	Response clone(GitlabCloneReq req);

}