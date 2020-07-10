package xyz.rexlin600.helloworld.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Wechat properties
 *
 * @author hekunlin
 */
@Data
@ToString
@Component
@ConfigurationProperties("rexlin600.wechat")
public class WechatProperties {

	/**
	 * Map
	 */
	private Map<String, String> map;

}