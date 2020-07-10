package xyz.rexlin600.mail.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Mail func enum
 *
 * @author hekunlin
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum MailFuncEnum {

	/**
	 * Func notification
	 */
	FUNC_NOTIFICATION(201, "通知邮件", "notification mail message"),
	/**
	 * Func remind
	 */
	FUNC_REMIND(202, "提醒邮件", "remind mail message"),
	/**
	 * Func activity
	 */
	FUNC_ACTIVITY(203, "活动邮件", "activity mail message");


	/**
	 * Code
	 */
	private int code;
	/**
	 * Zh
	 */
	private String zh;
	/**
	 * En
	 */
	private String en;
}