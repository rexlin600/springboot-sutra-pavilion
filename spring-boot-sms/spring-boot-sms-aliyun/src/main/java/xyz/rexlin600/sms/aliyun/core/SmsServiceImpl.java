package xyz.rexlin600.sms.aliyun.core;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.rexlin600.sms.aliyun.core.cache.CacheHelper;
import xyz.rexlin600.sms.aliyun.core.cache.CheckHelper;
import xyz.rexlin600.sms.aliyun.core.constant.SmsConst;
import xyz.rexlin600.sms.aliyun.core.request.SmsRequest;
import xyz.rexlin600.sms.aliyun.core.request.VerifyCodeRequest;

import java.time.Instant;
import java.util.Map;

/**
 * The type Sms service.
 *
 * @author hekunlin
 * @since 2020 /9/2
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

	/**
	 * The Sms client.
	 */
	private final SmsClient smsClient;

	/**
	 * The Cache helper.
	 */
	private final CacheHelper cacheHelper;

	/**
	 * The Check helper.
	 */
	private final CheckHelper checkHelper;

	/**
	 * Instantiates a new Sms service.
	 *
	 * @param smsClient   the sms client
	 * @param cacheHelper the cache helper
	 * @param checkHelper the check helper
	 */
	@Autowired
	public SmsServiceImpl(SmsClient smsClient,
						  CacheHelper cacheHelper,
						  CheckHelper checkHelper) {
		this.smsClient = smsClient;
		this.cacheHelper = cacheHelper;
		this.checkHelper = checkHelper;
	}

	/**
	 * 发送验证码短信
	 *
	 * @param req the req
	 * @throws ClientException the client exception
	 */
	@Override
	public void sendVerifyCodeSms(SmsRequest req) throws ClientException {
		// 发送验证码标识不正确
		if (BooleanUtil.isFalse(req.getIsVerifyCode())) {
			throw new ClientException(SmsConst.ERROR_CODE, "发送验证码短信标识不合法，原因：isVerifyCode=false");
		}

		// 没有验证码code
		Map<String, String> param = req.getTemplateParam();
		if (MapUtil.isEmpty(param) || !param.containsKey(SmsConst.CODE)) {
			throw new ClientException(SmsConst.ERROR_CODE, "发送验证码短信参数不合法，原因：没有code");
		}

		// 发送消息
		smsClient.send(req);

		// 缓存 code 值
		if (BooleanUtil.isTrue(req.getIsVerifyCode()) && BooleanUtil.isFalse(checkHelper.isGreenChannelOpen())) {
			cacheHelper.cacheVerifyCode(req.getPhone(), req.getTemplateCode(), req.getTemplateParam().get(SmsConst.CODE));
		}

		// 日志记录
		printSendLog(req);
	}


	/**
	 * 发送通知短信
	 *
	 * @param req the req
	 * @throws ClientException the client exception
	 */
	@Override
	public void sendNotifySms(SmsRequest req) throws ClientException {
		// 发送通知短信标识不正确
		if (BooleanUtil.isTrue(req.getIsVerifyCode())) {
			throw new ClientException(SmsConst.ERROR_CODE, "发送通知短信标识不合法，原因：isVerifyCode=true");
		}

		// 发送消息
		smsClient.send(req);

		// 日志记录
		printSendLog(req);
	}


	/**
	 * 校验验证码
	 *
	 * @param request the request
	 * @throws ClientException the client exception
	 */
	@Override
	public void verifyCode(VerifyCodeRequest request) throws ClientException {
		checkHelper.isEqualVerifyCode(request);
	}

	// -----------------------------------------------------------------------------------------------
	// EXTRA METHOD
	// -----------------------------------------------------------------------------------------------

	/**
	 * Print send log.
	 *
	 * @param req the req
	 */
	private void printSendLog(SmsRequest req) {
		log.info("==>  手机号 {} 发送短信 {} 成功, 参数 {}，时间 {}",
				req.getPhone(),
				req.getTemplateCode(),
				JSONUtil.toJsonStr(req.getTemplateParam()),
				Instant.now().toEpochMilli());
	}

}