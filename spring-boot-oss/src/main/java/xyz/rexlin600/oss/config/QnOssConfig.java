package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.Serializable;

/**
 * Qn oss config
 *
 * @author hekunlin
 */
@Qualifier(value = "qnOssConfig")
@ConditionalOnProperty(prefix = OssConstant.PREFIX_QN, name = "enable", havingValue = "true")
@Data
@Configuration
@ConfigurationProperties(prefix = OssConstant.PREFIX_QN)
public class QnOssConfig implements Serializable {

	/**
	 * Enable
	 */
	private Boolean enable;

	/**
	 * Domain
	 */
	private String domain;

	/**
	 * Access key
	 */
	private String accessKey;

	/**
	 * Secret key
	 */
	private String secretKey;

	/**
	 * Bucket name
	 */
	private String bucketName;

}