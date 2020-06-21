package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 腾讯云配置
 *
 * @author: hekunlin
 * @date: 2020/6/21
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oss.tencent")
public class TxOssConfig implements Serializable {

    /**
     * 腾讯云绑定的域名
     */
    private String domain;

    /**
     * 腾讯云所属地区
     */
    private String region;

    /**
     * 腾讯云路径前缀
     */
    private String prefix;

    /**
     * 腾讯云AppId
     */
    private String appId;

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