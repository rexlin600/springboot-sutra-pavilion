package xyz.rexlin600.sms.aliyun.core;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import xyz.rexlin600.sms.aliyun.config.sms.SmsConfig;
import xyz.rexlin600.sms.aliyun.core.cache.CacheHelper;
import xyz.rexlin600.sms.aliyun.core.cache.CheckHelper;
import xyz.rexlin600.sms.aliyun.core.constant.SmsConst;
import xyz.rexlin600.sms.aliyun.core.request.SmsRequest;
import xyz.rexlin600.sms.aliyun.core.request.SmsResponse;

/**
 * 阿里云信实例
 *
 * @author hekunlin
 * @since 2020 /9/2
 */
@Slf4j
@Component
public class SmsClient {

	/**
	 * The constant instance.
	 */
	public static IAcsClient instance = null;

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
	 * 构造器
	 *
	 * @param smsConfig   the sms config
	 * @param checkHelper
	 * @param cacheHelper
	 */
	@Autowired
	public SmsClient(SmsConfig smsConfig,
					 CheckHelper checkHelper,
					 CacheHelper cacheHelper) {
		this.smsConfig = smsConfig;
		this.checkHelper = checkHelper;
		this.cacheHelper = cacheHelper;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		log.info("==>  初始化 SMS instance 开始 ...");
		if (instance == null) {
			synchronized (SmsClient.class) {
				if (instance == null) {
					DefaultProfile profile = DefaultProfile.getProfile(
							smsConfig.getRegionId(),
							smsConfig.getAccessKey(),
							smsConfig.getAccessSecret());
					instance = new DefaultAcsClient(profile);
					log.info("==>  初始化 SMS instance 完成 ...");
				}
			}
		}
	}


	/**
	 * 发送消息
	 *
	 * @param req the req
	 * @return the boolean
	 * @throws ClientException the client exception
	 */
	public void send(SmsRequest req) throws ClientException {
		// 绿色通道校验
		if (checkHelper.isGreenChannelOpen()) {
			log.info("==>  已开启短信绿色通道，手机号 {} 默认短信验证码 {}", req.getPhone(), smsConfig.getGreenCode());
			return;
		}

		// 超过阈值校验
		checkHelper.isOverThreshold(req.getPhone(), req.getTemplateCode());

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