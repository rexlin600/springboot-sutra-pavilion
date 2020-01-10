package xyz.rexlin600.common.enums;

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

    SUCCESS("Success", 200, "邮件发送成功", "mail send success"),
    FAILED("Success", 400, "邮件发送失败", "mail send failed"),
    STOP("ServiceUnavailable", 503, "服务不可用", "service unavailable"),
    InternalError("InternalError", 500, "内部错误", "internal error"),
    MissingParameter("MissingParameter", 400, "缺少必填参数", "missing parameter"),
    InsufficientPermissions("InsufficientPermissions", 400, "权限不足", "insufficient permissions");

    private String msg;
    private Integer code;
    private String zh;
    private String en;

}