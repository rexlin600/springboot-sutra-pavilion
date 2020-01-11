package xyz.rexlin600.mail.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * MailTypeEnum 枚举类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MailTypeEnum {

    TYPE_SIMPLE(100, "文本邮件", "text mail message"),
    TYPE_HTML(101, "HTML邮件", "html mail message"),
    TYPE_ATTACHMENTS(102, "附件邮件", "attachment mail message"),
    TYPE_INLINE_RESOURCE(103, "资源邮件", "resource mail message");

    private int code;
    private String zh;
    private String en;

}