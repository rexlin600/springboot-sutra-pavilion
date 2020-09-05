package xyz.rexlin600.sms.aliyun.core;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xyz.rexlin600.sms.aliyun.core.client.SmsClient;
import xyz.rexlin600.sms.aliyun.core.config.SmsConfig;
import xyz.rexlin600.sms.aliyun.core.constant.SmsConst;
import xyz.rexlin600.sms.aliyun.core.helper.CacheHelper;
import xyz.rexlin600.sms.aliyun.core.helper.CheckHelper;
import xyz.rexlin600.sms.aliyun.core.request.SmsRequest;
import xyz.rexlin600.sms.aliyun.core.request.SmsResponse;
import xyz.rexlin600.sms.aliyun.core.request.VerifyCodeRequest;

import java.time.Instant;

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
	private final SmsConfig smsConfig;

	/**
	 * The sms helper service.
	 */
	private final CheckHelper checkHelper;
	/**
	 * The Cache helper.
	 */
	private final CacheHelper cacheHelper;

	/**
	 * Instantiates a new Sms service.
	 *
	 * @param smsConfig   the sms config
	 * @param checkHelper the sms helper service
	 * @param cacheHelper the cache helper
	 */
	@Autowired
	public SmsServiceImpl(SmsConfig smsConfig,
						  CheckHelper checkHelper,
						  CacheHelper cacheHelper) {
		this.smsConfig = smsConfig;
		this.checkHelper = checkHelper;
		this.cacheHelper = cacheHelper;
	}

	// -----------------------------------------------------------------------------------------------
	// 短信服务
	// -----------------------------------------------------------------------------------------------

	/**
	 * 发送短信
	 *
	 * @param req the req
	 * @return the boolean
	 * @throws ClientException client exception
	 */
	@Override
	public void sendSms(SmsRequest req) throws ClientException {
		// 开启绿色通道不发送短信
		if (checkHelper.isChannelOpen()) {
			log.info("==>  已开启短信绿色通道，默认短信验证码 {}", smsConfig.getGreenCode());
			return;
		}

		// 发送验证码需校验模板参数是否包含 code
		boolean isVerifyCode = req.getIsVerifyCode();
		if (isVerifyCode) {
			checkHelper.isContainCode(req.getTemplateParam());
		}

		// 防盗刷：发送间隔阈值、每日阈值、总模板阈值等校验
		checkHelper.isOverIntervalThreshold(req.getPhone(), req.getTemplateCode());
		checkHelper.isOverDailyThreshold(req.getPhone(), req.getTemplateCode());
		checkHelper.isOverTemplateThreshold(req.getPhone());

		// 发送消息
		send(req);

		// 发送短信验证码需缓存验证码
		if (isVerifyCode) {
			cacheHelper.cacheVerifyCode(req.getPhone(), req.getTemplateCode(), req.getTemplateParam().get(SmsConst.CODE));
		}

		// 日志记录
		log.info("==>  手机号 {} 发送模板短信 {} 成功，时间 {}", req.getPhone(), req.getTemplateCode(), Instant.now().toEpochMilli());
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
	// 发送短信
	// -----------------------------------------------------------------------------------------------

	/**
	 * 发送消息
	 *
	 * @param req the req
	 * @return the boolean
	 * @throws ClientException the client exception
	 */
	private void send(SmsRequest req) throws ClientException {
		// 构建通用短信请求对象
		CommonRequest commonRequest = new CommonRequest();
		commonRequest.setSysMethod(MethodType.POST);
		commonRequest.setSysAction(SmsConst.SEND_SMS);
		commonRequest.setSysDomain(smsConfig.getDomain());
		commonRequest.setSysVersion(SmsConst.VERSION);
		commonRequest.putQueryParameter(SmsConst.REGION_PARAM, smsConfig.getRegionId());
		commonRequest.putQueryParameter("PhoneNumbers", req.getPhone());
		commonRequest.putQueryParameter("SignName", req.getSignName());
		commonRequest.putQueryParameter("TemplateCode", req.getTemplateCode());
		commonRequest.putQueryParameter("TemplateParam", JSONUtil.toJsonStr(req.getTemplateParam()));

		// 发送短信
		CommonResponse response = SmsClient.instance.getCommonResponse(commonRequest);
		if (response.getHttpStatus() == HttpStatus.OK.value()) {
			JSONObject jsonObject = JSONUtil.parseObj(response.getData());
			SmsResponse smsResponse = SmsResponse.build(jsonObject);
			if (!smsResponse.isSuccess()) {
				throw new ClientException(SmsConst.ERROR_CODE, smsResponse.getMessage());
			}

			// 发送成功后需记录阈值防盗刷：每日阈值、模板阈值
			cacheHelper.cacheDailyThreshold(req.getPhone(), req.getTemplateCode());
			cacheHelper.cacheTemplateThreshold(req.getPhone(), req.getTemplateCode());
		}
	}

}