package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.Serializable;

/**
 * 腾讯云配置
 *
 * @author: hekunlin
 * @since: 2020/6/21
 */
@Qualifier(value = "txOssConfig")
@ConditionalOnProperty(prefix = OssConstant.PREFIX_TX, name = "enable", havingValue = "true")
@Data
@Configuration
@ConfigurationProperties(prefix = OssConstant.PREFIX_TX)
public class TxOssConfig implements Serializable {

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 腾讯云绑定的域名
     */
    private String domain;

    /**
     * 腾讯云所属地区
     */
    private String region;

    /**
     * 腾讯云 secretId
     */
    private String secretId;

    /**
     * 腾讯云 secretKey
     */
    private String secretKey;

    /**
     * 腾讯云 bucket 名称
     */
    private String bucketName;

}