package xyz.rexlin600.mail.service;

/**
 * MailMessageBiz 服务类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
public interface MailService {

    /**
     * 发送简单邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送 HTML 邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param to       接收人
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filePath 附件地址
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送正文中有静态资源（图片）的邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param rscPath 资源地址
     * @param rscId   资源ID
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}