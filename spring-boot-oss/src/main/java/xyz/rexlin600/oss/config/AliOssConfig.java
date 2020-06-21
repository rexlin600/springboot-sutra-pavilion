package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 阿里云OSS配置
 *
 * @author: hekunlin
 * @date: 2020/6/21
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oss.aliyun")
public class AliOssConfig implements Serializable {

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