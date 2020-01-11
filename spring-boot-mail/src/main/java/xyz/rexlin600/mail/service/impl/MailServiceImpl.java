package xyz.rexlin600.elasticsearch.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import xyz.rexlin600.github.common.enums.MailFuncEnum;
import xyz.rexlin600.github.common.enums.MailSvcEnum;
import xyz.rexlin600.github.common.enums.MailTypeEnum;
import xyz.rexlin600.elasticsearch.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.Clock;
import java.time.Instant;

/**
 * MailMessageBiz 服务类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // 发件人，默认配置的邮件用户
    @Value("${spring.mail.username}")
    private String from;

    // -----------------------------------------------------------------------------------------------
    // MAIL IMPL SERVICE
    // -----------------------------------------------------------------------------------------------

    /**
     * 发送简单邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("==>  发送邮件：类型=[{}] 功能=[{}] 发送人=[{}] 接收人=[{}] 状态=[{}] 时间=[{}]",
                    MailTypeEnum.TYPE_SIMPLE.getZh(),
                    MailFuncEnum.FUNC_NOTIFICATION.getZh(),
                    from,
                    to,
                    MailSvcEnum.SUCCESS.getMsg(),
                    Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        } catch (Exception e) {
            log.info("==>  发送邮件：类型=[{}] 功能=[{}] 发送人=[{}] 接收人=[{}] 错误信息=[{}] 时间=[{}]",
                    MailTypeEnum.TYPE_SIMPLE.getZh(),
                    MailFuncEnum.FUNC_NOTIFICATION.getZh(),
                    from,
                    to,
                    e.getMessage(),
                    Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        }
    }

    /**
     * 发送 HTML 邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);

            log.info("==>  发送邮件：类型=[{}] 功能=[{}] 发送人=[{}] 接收人=[{}] 状态=[{}] 时间=[{}]",
                    MailTypeEnum.TYPE_HTML.getZh(),
                    MailFuncEnum.FUNC_NOTIFICATION.getZh(),
                    from,
                    to,
                    MailSvcEnum.SUCCESS.getMsg(),
                    Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        } catch (MessagingException e) {
            log.info("==>  发送邮件：类型=[{}] 功能=[{}] 发送人=[{}] 接收人=[{}] 错误信息=[{}] 时间=[{}]",
                    MailTypeEnum.TYPE_HTML.getZh(),
                    MailFuncEnum.FUNC_NOTIFICATION.getZh(),
                    from,
                    to,
                    e.getMessage(),
                    Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to       接收人
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filePath 附件地址
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            //helper.addAttachment("test"+fileName, file);

            mailSender.send(message);

            log.info("==>  发送邮件：类型=[{}] 功能=[{}] 发送人=[{}] 接收人=[{}] 状态=[{}] 时间=[{}]",
                    MailTypeEnum.TYPE_ATTACHMENTS.getZh(),
                    MailFuncEnum.FUNC_NOTIFICATION.getZh(),
                    from,
                    to,
                    MailSvcEnum.SUCCESS.getMsg(),
                    Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        } catch (MessagingException e) {
            log.info("==>  发送邮件：类型=[{}] 功能=[{}] 发送人=[{}] 接收人=[{}] 错误信息=[{}] 时间=[{}]",
                    MailTypeEnum.TYPE_ATTACHMENTS.getZh(),
                    MailFuncEnum.FUNC_NOTIFICATION.getZh(),
                    from,
                    to,
                    e.getMessage(),
                    Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        }
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param rscPath 资源地址
     * @param rscId   资源ID
     */
    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);

            log.info("==>  发送邮件：类型=[{}] 功能=[{}] 发送人=[{}] 接收人=[{}] 状态=[{}] 时间=[{}]",
                    MailTypeEnum.TYPE_INLINE_RESOURCE.getZh(),
                    MailFuncEnum.FUNC_NOTIFICATION.getZh(),
                    from,
                    to,
                    MailSvcEnum.SUCCESS.getMsg(),
                    Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        } catch (MessagingException e) {
            log.info("==>  发送邮件：类型=[{}] 功能=[{}] 发送人=[{}] 接收人=[{}] 错误信息=[{}] 时间=[{}]",
                    MailTypeEnum.TYPE_INLINE_RESOURCE.getZh(),
                    MailFuncEnum.FUNC_NOTIFICATION.getZh(),
                    from,
                    to,
                    e.getMessage(),
                    Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        }
    }
}