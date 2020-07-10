package xyz.rexlin600.mail.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Mail type enum
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MailTypeEnum {

	/**
	 * Type simple
	 */
	TYPE_SIMPLE(100, "文本邮件", "text mail message"),
	/**
	 * Type html
	 */
	TYPE_HTML(101, "HTML邮件", "html mail message"),
	/**
	 * Type attachments
	 */
	TYPE_ATTACHMENTS(102, "附件邮件", "attachment mail message"),
	/**
	 * Type inline resource
	 */
	TYPE_INLINE_RESOURCE(103, "资源邮件", "resource mail message");

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