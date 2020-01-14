package xyz.rexlin600.mybatisplus.codegen.common.req;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * DataSourceReq 请求类
 *
 * @author: hekunlin
 * @date: 2020/1/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DataSourceReq implements Serializable {

    /**
     * 数据源ID
     */
    private Long id;

    /**
     * 数据库地址
     */
    @NotEmpty
    private String host;

    /**
     * 端口
     */
    @NotEmpty
    private String port;

    /**
     * 用户名
     */
    @NotEmpty
    private String username;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 数据库名称
     */
    @NotEmpty
    private String dbName;

}