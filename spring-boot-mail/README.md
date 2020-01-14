# 简介

集成 `mail` 邮件服务，提供如下类型的邮件发送：

- 文本
- HTML
- 附件
- 内嵌资源
- 模板邮件

## 开始发车

* 引入依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
```

* yaml 配置

> 这里是我的测试邮箱账号，请注意替换为自己的邮箱账号

```yaml
spring:
  mail:
    host: smtp.163.com
    username: rexlin500@163.com
    password: springboot123456
    default-encoding: UTF-8
    properties:
      mail:
        smtp:
          auth: true
          socketFactory:
            port: 465
            class: javax.net.ssl.SSLSocketFactory
            fallback: false
          starttls:
            enable: true
            required: true
```

* 定义常量、枚举、请求类等

> 参考 `xyz.rexlin600.mail.common` 下的代码

* 服务实现

```java
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 发件人，默认配置的邮件用户
     */
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
```

* 测试

运行 `xyz.rexlin600.mail.service.impl.MailServiceImplTest` 即可

