package xyz.rexlin600.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import xyz.rexlin600.service.MailService;

import java.util.UUID;


/**
 * @description
 * @auther hekunlin
 * @create 2020-01-10 16:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail(
                "3072054267@qq.com",
                "Simple-Mail",
                "This is a Simple Mail, Just for Test");
    }

    @Test
    public void sendHtmlMail() {
        // html content
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>Hi, This is a Html Mail, Just for Test</h3>\n" +
                "</body>\n" +
                "</html>";

        mailService.sendSimpleMail(
                "3072054267@qq.com",
                "Html-Mail",
                "This is a Html Mail, Just for Test");
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath = "E:\\Git_Workspace\\springboot2-example\\README.md";

        mailService.sendAttachmentsMail("3072054267@qq.com",
                "Attachments-Mail",
                "This is a Attachments Mail, Just for Test",
                filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = UUID.randomUUID().toString().replace("-", "");
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\hekunlin\\Pictures\\golang.jpg";

        mailService.sendInlineResourceMail(
                "3072054267@qq.com",
                "InlineResource-Mail",
                content,
                imgPath,
                rscId);
    }

    @Test
    public void sendTemplateMail() {
        // 创建邮件正文
        Context context = new Context();
        context.setVariable("id", UUID.randomUUID().toString().replace("-", ""));
        String emailContent = templateEngine.process("testTemplate", context);

        mailService.sendHtmlMail(
                "3072054267@qq.com",
                "Template-Mail",
                emailContent);
    }


}