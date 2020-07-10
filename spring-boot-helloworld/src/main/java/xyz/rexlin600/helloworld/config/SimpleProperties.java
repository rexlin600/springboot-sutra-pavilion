package xyz.rexlin600.helloworld.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Simple properties
 *
 * @author hekunlin
 */
@SuppressWarnings("ALL")
@ToString
@Data
@Component
@ConfigurationProperties(prefix = "rexlin600.simple.properties")
public class SimpleProperties {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Web url
	 */
	private String webUrl;

	/**
	 * Random str
	 */
	private String randomStr;

	/**
	 * Random int
	 */
	private String randomInt;

	/**
	 * Random long
	 */
	private String randomLong;

	/**
	 * Random int limit
	 */
	private String randomIntLimit;

	/**
	 * Random long limit
	 */
	private String randomLongLimit;

}