package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.Serializable;

/**
 * Ali oss config
 *
 * @author hekunlin
 */
@Qualifier(value = "aliOssConfig")
@ConditionalOnProperty(prefix = OssConstant.PREFIX_ALI, name = "enable", havingValue = "true")
@Data
@Configuration
@ConfigurationProperties(prefix = OssConstant.PREFIX_ALI)
public class AliOssConfig implements Serializable {

	/**
	 * Enable
	 */
	private boolean enable;

	/**
	 * Domain
	 */
	private String domain;

	/**
	 * Endpoint
	 */
	private String endpoint;

	/**
	 * Access key
	 */
	private String accessKey;

	/**
	 * Access key secret
	 */
	private String accessKeySecret;

	/**
	 * Bucket name
	 */
	private String bucketName;

}