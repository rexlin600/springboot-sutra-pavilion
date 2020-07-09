package xyz.rexlin600.gitlab.biz;

import xyz.rexlin600.gitlab.common.apiparam.Response;
import xyz.rexlin600.gitlab.req.GitlabCloneReq;

/**
 * GitlabBiz 业务类
 *
 * @author: rexlin600
 * @since: 2020-02-15
 */
public interface GitlabService {

    /**
     * 查询所有项目
     * @param req
     * @return
     */
    Response list(GitlabCloneReq req);

    /**
     * 克隆项目
     *
     * @param req
     * @return
     */
    Response clone(GitlabCloneReq req);

}