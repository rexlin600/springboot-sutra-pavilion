package xyz.rexlin600.fasdfs.configbean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Fast dfs config bean
 *
 * @author hekunlin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "biz.fastdfs")
public class FastDfsConfigBean {

	/**
	 * Http url
	 */
	private String httpUrl;

}