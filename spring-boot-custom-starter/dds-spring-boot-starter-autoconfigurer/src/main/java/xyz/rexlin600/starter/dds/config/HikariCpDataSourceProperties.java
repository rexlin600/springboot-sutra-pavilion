package xyz.rexlin600.starter.dds.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Hikari cp data source properties
 *
 * @author hekunlin
 */
@Data
@Component
@ConfigurationProperties("spring.datasource")
public class HikariCpDataSourceProperties {

	/**
	 * Username
	 */
	private String username;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Url
	 */
	private String url;

	/**
	 * Driver class name
	 */
	private String driverClassName;

}