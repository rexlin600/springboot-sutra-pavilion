package xyz.rexlin600.sms.aliyun.core;

import com.aliyuncs.exceptions.ClientException;
import xyz.rexlin600.sms.aliyun.core.request.SmsRequest;
import xyz.rexlin600.sms.aliyun.core.request.VerifyCodeRequest;

/**
 * 阿里云短信服务接口
 *
 * @author hekunlin
 * @since 2020 /9/3
 */
public interface SmsService {

	/**
	 * 发送验证码短信
	 *
	 * @param req the req
	 * @throws ClientException the client exception
	 */
	void sendVerifyCodeSms(SmsRequest req) throws ClientException;


	/**
	 * 发送通知短信
	 *
	 * @param req the req
	 * @throws ClientException the client exception
	 */
	void sendNotifySms(SmsRequest req) throws ClientException;


	/**
	 * 校验Code
	 *
	 * @param request the request
	 * @throws ClientException the client exception
	 */
	void verifyCode(VerifyCodeRequest request) throws ClientException;

}