package xyz.rexlin600.sms.aliyun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Sms config.
 *
 * @author hekunlin
 * @since 2020 /9/2
 */
@Data
@Configuration
@ConfigurationProperties("sms.ali")
public class SmsConfig {

	/**
	 * 短信API产品域名（接口地址固定，无需修改）
	 */
	private String domain;

	/**
	 * The Region id.
	 */
	private String regionId;

	/**
	 * The Access key.
	 */
	private String accessKey;

	/**
	 * The Access secret.
	 */
	private String accessSecret;

	/**
	 * 全局开关：绿色通道，是否发送短信，默认为 false
	 * <p>
	 * true：打开绿色通道，不发短信
	 * false：关闭绿色通道，发送短信
	 */
	private Boolean greenChannel;

	/**
	 * 绿色通道开启时的默认 code
	 */
	private String greenCode;

}