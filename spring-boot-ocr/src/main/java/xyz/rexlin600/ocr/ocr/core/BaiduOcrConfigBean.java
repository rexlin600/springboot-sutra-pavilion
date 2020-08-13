package xyz.rexlin600.ocr.ocr.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 百度 OCR 配置
 *
 * @author hekunlin
 * @since 2020 /8/13
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "baidu.ocr")
public class BaiduOcrConfigBean {

	/**
	 * App id
	 */
	private String appId;

	/**
	 * App key
	 */
	private String appKey;

	/**
	 * App secret key
	 */
	private String appSecretKey;

}