package xyz.rexlin600.common.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * AttachmentsMailReq 类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttachmentsMailReq implements Serializable {

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

    /**
     * 附件 URL 内容
     */
    private String filePath;

}
