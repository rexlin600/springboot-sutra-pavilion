package xyz.rexlin600.mail.common.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * HtmlMailReq 类
 *
 * @author: hekunlin
 * @since: 2020/1/10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HtmlMailReq implements Serializable {

    /**
     * 发送给哪个用户
     */
    private String to;

    /**
     * 发送的右键主题
     */
    private String subject;

    /**
     * 发送的邮件内容
     */
    private String content;

}
