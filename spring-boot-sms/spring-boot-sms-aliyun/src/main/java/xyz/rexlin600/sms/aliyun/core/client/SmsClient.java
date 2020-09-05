package xyz.rexlin600.sms.aliyun.core.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.sms.aliyun.core.config.SmsConfig;

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
	 * The Sms config.
	 */
	private final SmsConfig smsConfig;

	/**
	 * 构造器
	 *
	 * @param smsConfig the sms config
	 */
	@Autowired
	public SmsClient(SmsConfig smsConfig) {
		this.smsConfig = smsConfig;
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

}