package xyz.rexlin600.mail.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * MailFuncEnum 枚举类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum MailFuncEnum {

    /**
     * 通知
     */
    FUNC_NOTIFICATION(201, "通知邮件", "notification mail message"),
    /**
     * 提醒
     */
    FUNC_REMIND(202, "提醒邮件", "remind mail message"),
    /**
     * 活动
     */
    FUNC_ACTIVITY(203, "活动邮件", "activity mail message");


    private int code;
    private String zh;
    private String en;
}