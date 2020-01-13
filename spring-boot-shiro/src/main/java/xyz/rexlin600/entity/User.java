package xyz.rexlin600.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * User 实体类
 *
 * @author: rexlin600
 * @date: 2020-01-13
 */
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 密码盐值
     */
    private String salt;

}