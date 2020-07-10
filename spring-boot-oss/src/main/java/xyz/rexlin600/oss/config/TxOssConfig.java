package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.Serializable;

/**
 * Tx oss config
 *
 * @author hekunlin
 */
@Qualifier(value = "txOssConfig")
@ConditionalOnProperty(prefix = OssConstant.PREFIX_TX, name = "enable", havingValue = "true")
@Data
@Configuration
@ConfigurationProperties(prefix = OssConstant.PREFIX_TX)
public class TxOssConfig implements Serializable {

	/**
	 * Enable
	 */
	private Boolean enable;

	/**
	 * Domain
	 */
	private String domain;

	/**
	 * Region
	 */
	private String region;

	/**
	 * Secret id
	 */
	private String secretId;

	/**
	 * Secret key
	 */
	private String secretKey;

	/**
	 * Bucket name
	 */
	private String bucketName;

}