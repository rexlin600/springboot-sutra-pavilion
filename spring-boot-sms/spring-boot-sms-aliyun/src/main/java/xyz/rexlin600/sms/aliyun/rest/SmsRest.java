package xyz.rexlin600.sms.aliyun.rest;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.sms.aliyun.core.request.SmsRequest;
import xyz.rexlin600.sms.aliyun.core.request.VerifyCodeRequest;
import xyz.rexlin600.sms.aliyun.service.SmsService;

import javax.validation.Valid;

/**
 * The type Sms rest.
 *
 * @author hekunlin
 * @since 2020 /9/3
 */
@Validated
@RestController
@RequestMapping("/sms/ali")
public class SmsRest {

	/**
	 * The Sms service.
	 */
	private final SmsService smsService;

	/**
	 * Instantiates a new Sms rest.
	 *
	 * @param smsService the sms service
	 */
	@Autowired
	public SmsRest(SmsService smsService) {
		this.smsService = smsService;
	}


	/**
	 * Send sms.
	 *
	 * @param request the request
	 */
	@SneakyThrows
	@PostMapping("/sendSms")
	public void sendSms(@RequestBody @Valid SmsRequest request) {
		smsService.sendSms(request);
	}

	/**
	 * Verify.
	 *
	 * @param request the request
	 */
	@SneakyThrows
	@PostMapping("/verify")
	public void verify(@RequestBody @Valid VerifyCodeRequest request) {
		smsService.verifyCode(request);
	}

}