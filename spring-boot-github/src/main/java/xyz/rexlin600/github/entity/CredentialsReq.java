package xyz.rexlin600.github.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Github请求类
 *
 * @author: hekunlin
 * @since: 2020/1/3
 */
@Data
public class CredentialsReq implements Serializable {

    /**
     * Github用户名
     */
    private String username;

    /**
     * Github密码
     */
    private String password;

}