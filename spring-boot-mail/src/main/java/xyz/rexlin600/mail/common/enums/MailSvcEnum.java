package xyz.rexlin600.mail.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Mail svc enum
 *
 * @author hekunlin
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum MailSvcEnum {

	/**
	 * Success
	 */
	SUCCESS("Success", 200, "邮件发送成功", "mail send success"),
	/**
	 * Failed
	 */
	FAILED("Success", 400, "邮件发送失败", "mail send failed"),
	/**
	 * Stop
	 */
	STOP("ServiceUnavailable", 503, "服务不可用", "service unavailable"),
	/**
	 * Internal error
	 */
	InternalError("InternalError", 500, "内部错误", "internal error"),
	/**
	 * Missing parameter
	 */
	MissingParameter("MissingParameter", 400, "缺少必填参数", "missing parameter"),
	/**
	 * Insufficient permissions
	 */
	InsufficientPermissions("InsufficientPermissions", 400, "权限不足", "insufficient resources");

	/**
	 * Msg
	 */
	private String msg;
	/**
	 * Code
	 */
	private Integer code;
	/**
	 * Zh
	 */
	private String zh;
	/**
	 * En
	 */
	private String en;

}