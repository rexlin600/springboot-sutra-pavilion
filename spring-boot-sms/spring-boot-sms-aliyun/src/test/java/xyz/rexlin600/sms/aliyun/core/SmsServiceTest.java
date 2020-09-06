package xyz.rexlin600.sms.aliyun.core;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.sms.aliyun.core.request.SmsRequest;
import xyz.rexlin600.sms.aliyun.core.request.VerifyCodeRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Sms service test.
 *
 * @author rexlin600
 * @date: 2020 /9/6
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsServiceTest {

	/**
	 * The Sms service.
	 */
	@Autowired
	private SmsService smsService;

	/**
	 * Send verify code sms.
	 */
	@SneakyThrows
	@Test
	public void sendVerifyCodeSms() {
		SmsRequest smsRequest = new SmsRequest();
		smsRequest.setIsVerifyCode(true);
		smsRequest.setPhone("17628066212");
		Map<String, String> templateParam = new HashMap<>();
		templateParam.put("code", "123456");
		smsRequest.setTemplateParam(templateParam);
		// 登陆确认验证码
		smsRequest.setTemplateCode("SMS_154170069");
		smsRequest.setSignName("海康交通大数据");

		smsService.sendVerifyCodeSms(smsRequest);
	}

	/**
	 * Send notify sms.
	 */
	@SneakyThrows
	@Test
	public void sendNotifySms() {
		SmsRequest smsRequest = new SmsRequest();
		smsRequest.setIsVerifyCode(false);
		smsRequest.setPhone("17628066212");
		Map<String, String> templateParam = new HashMap<>();
		templateParam.put("pwd", "876543");
		smsRequest.setTemplateParam(templateParam);
		// 随机密码通知
		smsRequest.setTemplateCode("SMS_176537745");
		smsRequest.setSignName("斑马信用");

		smsService.sendNotifySms(smsRequest);
	}

	/**
	 * Verify code.
	 */
	@SneakyThrows
	@Test
	public void verifyCode() {
		VerifyCodeRequest request = new VerifyCodeRequest();
		request.setPhone("17628066212");
		request.setCode("123456");
		request.setTemplateCode("SMS_154170069");
		smsService.verifyCode(request);
	}

}