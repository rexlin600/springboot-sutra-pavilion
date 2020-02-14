package xyz.rexlin600.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * GitlabCloneReq 请求类
 *
 * @author: rexlin600
 * @date: 2020-02-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitlabCloneReq implements Serializable {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 筛选的项目 -> 输出地址
     */
    private String outDir;

}