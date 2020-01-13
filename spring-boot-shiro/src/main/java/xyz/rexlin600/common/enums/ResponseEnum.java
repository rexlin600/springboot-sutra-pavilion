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
    PASSWORD_INCORRECT(1005, "密码错误"),
    /**
     * 权限不足
     */
    UNAUTHORIZED(1006, "权限不足");

    /**
     * 返回状态码
     */
    private final int code;

    /**
     * 描述
     */
    private final String msg;

}