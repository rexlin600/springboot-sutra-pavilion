package xyz.rexlin600.biz;

import xyz.rexlin600.common.apiparam.Response;
import xyz.rexlin600.req.GitlabCloneReq;

/**
 * GitlabBiz 业务类
 *
 * @author: rexlin600
 * @date: 2020-02-15
 */
public interface GitlabService {

    /**
     * 克隆项目
     *
     * @param req
     * @return
     */
    Response clone(GitlabCloneReq req);

}