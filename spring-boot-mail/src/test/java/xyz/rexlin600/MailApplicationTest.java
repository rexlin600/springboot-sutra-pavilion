package xyz.rexlin600;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTest {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Test
	public void contextTest() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom(from);
		mailMessage.setTo("3072054267@qq.com");
		mailMessage.setSubject("Simple Mail Test");
		mailMessage.setText("Test mail content");

		javaMailSender.send(mailMessage);
	}

}