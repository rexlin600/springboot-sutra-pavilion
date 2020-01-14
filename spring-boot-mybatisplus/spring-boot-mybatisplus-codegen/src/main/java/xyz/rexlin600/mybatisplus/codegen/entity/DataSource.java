package xyz.rexlin600.mybatisplus.codegen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * DataSource 实体类
 *
 * @author: hekunlin
 * @date: 2020/1/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataSource implements Serializable {

    /**
     * 数据源ID
     */
    private Long id;

    /**
     * 数据库地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}