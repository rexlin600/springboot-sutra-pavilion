package xyz.rexlin600.mail.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * MailSvcEnum 枚举类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum MailSvcEnum {

    /**
     * 邮件发送成功
     */
    SUCCESS("Success", 200, "邮件发送成功", "mail send success"),
    /**
     * 邮件发送失败
     */
    FAILED("Success", 400, "邮件发送失败", "mail send failed"),
    /**
     * 服务不可用
     */
    STOP("ServiceUnavailable", 503, "服务不可用", "service unavailable"),
    /**
     * 内部错误
     */
    InternalError("InternalError", 500, "内部错误", "internal error"),
    /**
     * 缺少必填参数
     */
    MissingParameter("MissingParameter", 400, "缺少必填参数", "missing parameter"),
    /**
     * 权限不足
     */
    InsufficientPermissions("InsufficientPermissions", 400, "权限不足", "insufficient permissions");

    private String msg;
    private Integer code;
    private String zh;
    private String en;

}