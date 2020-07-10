package xyz.rexlin600.gitlab.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Git lab config bean
 *
 * @author hekunlin
 */
@ToString
@Data
@Component
@ConfigurationProperties(prefix = "gitlab")
public class GitLabConfigBean {

	/**
	 * Host
	 */
	private String host;

	/**
	 * Token
	 */
	private String token;

	/**
	 * Username
	 */
	private String username;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Api namespace
	 */
	private String apiNamespace;

	/**
	 * Max time
	 */
	private Long maxTime;

}