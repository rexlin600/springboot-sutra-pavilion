package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.Serializable;

/**
 * 阿里云OSS配置
 *
 * @author: hekunlin
 * @since: 2020/6/21
 */
@Qualifier(value = "aliOssConfig")
@ConditionalOnProperty(prefix = OssConstant.PREFIX_ALI, name = "enable", havingValue = "true")
@Data
@Configuration
@ConfigurationProperties(prefix = OssConstant.PREFIX_ALI)
public class AliOssConfig implements Serializable {

    /**
     * 是否启用
     */
    private boolean enable;

    /**
     * 阿里云绑定的域名
     */
    private String domain;

    /**
     * 阿里云EndPoint
     */
    private String endpoint;

    /**
     * AccessKey 账号
     */
    private String accessKey;

    /**
     * AccessKeySecret 密钥
     */
    private String accessKeySecret;

    /**
     * 存储 bucket 名称
     */
    private String bucketName;

}