package xyz.rexlin600.helloworld.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Map properties
 *
 * @author hekunlin
 */
@Data
@ToString
@Component
@ConfigurationProperties("rexlin600.kv")
public class MapProperties {

	/**
	 * Map
	 */
	private Map<String, Object> map;

}