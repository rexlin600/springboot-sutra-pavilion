package xyz.rexlin600.sms.aliyun.core.helper;

import cn.hutool.core.map.MapUtil;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.rexlin600.sms.aliyun.config.SmsConfig;
import xyz.rexlin600.sms.aliyun.core.constant.SmsConst;
import xyz.rexlin600.sms.aliyun.core.request.VerifyCodeRequest;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * The type Sms check service.
 *
 * @author hekunlin
 * @since 2020 /9/2
 */
@Slf4j
@Service
public class CheckHelper {

	/**
	 * The Sms config.
	 */
	private final SmsConfig smsConfig;

	/**
	 * Redis template
	 */
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Instantiates a new Sms check service.
	 *
	 * @param smsConfig     the sms config
	 * @param redisTemplate the redis template
	 */
	@Autowired
	public CheckHelper(SmsConfig smsConfig,
					   RedisTemplate<String, Object> redisTemplate) {
		this.smsConfig = smsConfig;
		this.redisTemplate = redisTemplate;
	}

	// -----------------------------------------------------------------------------------------------
	// CHECK METHOD
	// -----------------------------------------------------------------------------------------------

	/**
	 * 检查绿色通道是否开启
	 *
	 * @return boolean boolean
	 */
	public boolean isChannelOpen() {
		return smsConfig.getGreenChannel();
	}

	/**
	 * 检查是否超过每日总阈值
	 *
	 * @param phone        the phone
	 * @param templateCode 短信模板Code
	 * @return the boolean
	 * @throws ClientException business exception
	 */
	public void isOverDailyThreshold(String phone, String templateCode) throws ClientException {
		String k1 = SmsConst.DAILY_THRESHOLD_KEY.concat(":")
				.concat(phone).concat(":")
				.concat(templateCode);
		int t1 = 0;
		Optional<Object> o1 = Optional.ofNullable(redisTemplate.opsForValue().get(k1));
		if (o1.isPresent()) {
			t1 = (int) o1.get();
		}

		if (t1 >= SmsConst.MAX_DAILY_THRESHOLD_VALUE) {
			log.info("==>  手机号为 {} 发送短信模板 Code为 {} 的总次数已超过每日阈值", phone, templateCode);
			throw new ClientException(SmsConst.ERROR_CODE, "今日发送短信已超过每日最大阈值");
		}
	}

	/**
	 * 检查是否超过模板总阈值
	 *
	 * @param phone the phone
	 * @return the boolean
	 * @throws ClientException business exception
	 */
	public void isOverTemplateThreshold(String phone) throws ClientException {
		String k1 = SmsConst.TEMPLATE_THRESHOLD_KEY.concat(":")
				.concat(phone);
		int t1 = 0;
		Optional<Object> o1 = Optional.ofNullable(redisTemplate.opsForValue().get(k1));
		if (o1.isPresent()) {
			t1 = (int) o1.get();
		}

		if (t1 >= SmsConst.MAX_TEMPLATE_THRESHOLD_VALUE) {
			log.info("==>  手机号为 {} 发送各类模板的总次数已超过模板总阈值", phone);
			throw new ClientException(SmsConst.ERROR_CODE, "今日发送短信已超过模板最大阈值");
		}
	}

	/**
	 * 检查是否超过时间间隔阈值
	 *
	 * @param phone        the phone
	 * @param templateCode 短信模板Code
	 * @return the boolean
	 * @throws ClientException business exception
	 */
	public void isOverIntervalThreshold(String phone, String templateCode) throws ClientException {
		String k1 = SmsConst.VERIFY_CODE_KEY.concat(":")
				.concat(phone).concat(":")
				.concat(templateCode);
		Long expireSeconds = redisTemplate.getExpire(k1, TimeUnit.SECONDS);
		if (expireSeconds != null
				&& expireSeconds > 0
				&& 600 - expireSeconds < SmsConst.MAX_INTERVAL_SECONDS_VALUE) {
			throw new ClientException(SmsConst.ERROR_CODE, "发送短信的间隔时间必须大于1分钟");
		}
	}

	/**
	 * 校验是否包含验证码参数
	 *
	 * @param templateParam the template param
	 */
	public void isContainCode(Map<String, String> templateParam) throws ClientException {
		if (MapUtil.isEmpty(templateParam) || !templateParam.containsKey(SmsConst.CODE)) {
			throw new ClientException(SmsConst.ERROR_CODE, "发送短信验证码时模板参数必须传入code");
		}
	}

	/**
	 * 立即清除指定 key
	 *
	 * @param key the key
	 */
	public void clearCodeImmediately(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * Is equal.
	 *
	 * @param request the request
	 * @throws ClientException the business exception
	 */
	public void isEqualVerifyCode(VerifyCodeRequest request) throws ClientException {
		String k1 = SmsConst.VERIFY_CODE_KEY.concat(":")
				.concat(request.getPhone()).concat(":")
				.concat(request.getTemplateCode());

		// 校验key是否存在
		Optional<Boolean> optional = Optional.ofNullable(redisTemplate.hasKey(k1));
		if (!optional.isPresent()) {
			throw new ClientException(SmsConst.ERROR_CODE, "验证码不存在或已失效");
		}

		// 校验值是否相等
		String cacheVerifyCode = String.valueOf(redisTemplate.opsForValue().get(k1));
		if (!cacheVerifyCode.equals(request.getCode())) {
			// 立即清除缓存 code
			clearCodeImmediately(k1);
			throw new ClientException(SmsConst.ERROR_CODE, "验证码不匹配");
		}
	}

}