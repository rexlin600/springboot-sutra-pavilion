package xyz.rexlin600.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResponseEnum 枚举类
 *
 * @author: rexlin600
 * @date: 2020-01-13
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS(2000, "成功"),
    /**
     * 失败
     */
    FAIL(-1, "失败"),
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(1004, "用户不存在"),
    /**
     * 密码错误
     */
    PASSWORD_INCORRECT(1014, "密码错误"),
    /**
     * 用户未激活
     */
    UN_ACTIVED(1015, "用户未激活"),
    /**
     * 认证失败
     */
    UN_AUTHENTICATION(1024, "认证失败"),
    /**
     * 权限不足
     */
    UN_AUTHORIZED(1034, "权限不足");

    /**
     * 返回状态码
     */
    private final int code;

    /**
     * 描述
     */
    private final String msg;

}