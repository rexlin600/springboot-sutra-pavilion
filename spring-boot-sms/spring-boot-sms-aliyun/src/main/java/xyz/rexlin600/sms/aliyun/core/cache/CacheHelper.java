package xyz.rexlin600.sms.aliyun.core.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.rexlin600.sms.aliyun.core.constant.SmsConst;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
public class CacheHelper {

	/**
	 * Redis template
	 */
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Instantiates a new Sms check service.
	 *
	 * @param redisTemplate the redis template
	 */
	@Autowired
	public CacheHelper(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// -----------------------------------------------------------------------------------------------
	// CACHE METHOD
	// -----------------------------------------------------------------------------------------------

	/**
	 * 缓存验证码
	 *
	 * @param phone        phone
	 * @param templateCode template code
	 * @param code         code
	 */
	public void cacheVerifyCode(String phone, String templateCode, String code) {
		String k1 = SmsConst.VERIFY_CODE_KEY.concat(":")
				.concat(phone).concat(":")
				.concat(templateCode);
		// 短信验证码保留5分钟
		redisTemplate.opsForValue().set(k1, code, 600, TimeUnit.SECONDS);
	}

	/**
	 * 缓存每日调用总数
	 *
	 * @param phone        the phone
	 * @param templateCode the template code
	 */
	public void cacheDailyThreshold(String phone, String templateCode) {
		String k1 = SmsConst.DAILY_THRESHOLD_KEY.concat(":")
				.concat(phone).concat(":")
				.concat(templateCode);
		Optional<Boolean> optional = Optional.ofNullable(redisTemplate.hasKey(k1));
		boolean hasKey = false;
		if (optional.isPresent()) {
			hasKey = optional.get();
		}

		if (hasKey) {
			// 每日阈值 +1
			redisTemplate.opsForValue().increment(k1, 1L);
		} else {
			// 每日阈值保留一天
			LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
			long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
			redisTemplate.opsForValue().set(k1, 1L, seconds, TimeUnit.SECONDS);
		}
	}

	/**
	 * 缓存模板调用总数
	 *
	 * @param phone        the phone
	 * @param templateCode the template code
	 */
	public void cacheTemplateThreshold(String phone, String templateCode) {
		String k1 = SmsConst.TEMPLATE_THRESHOLD_KEY.concat(":")
				.concat(phone);
		Optional<Boolean> optional = Optional.ofNullable(redisTemplate.hasKey(k1));
		boolean hasKey = false;
		if (optional.isPresent()) {
			hasKey = optional.get();
		}

		if (hasKey) {
			// 模板阈值 +1
			redisTemplate.opsForValue().increment(k1, 1L);
		} else {
			// 模板总阈值保留一天
			LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
			long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
			redisTemplate.opsForValue().set(k1, 1L, seconds, TimeUnit.SECONDS);
		}
	}

}