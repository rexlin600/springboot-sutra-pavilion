package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author: hekunlin
 * @date: 2020/6/21
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oss.qiniu")
public class QnOssConfig implements Serializable {

    /**
     * 七牛云 域名
     */
    private String domain;

    /**
     * 七牛云 accessKey
     */
    private String accessKey;

    /**
     * 七牛云 secretKey
     */
    private String secretKey;

    /**
     * 七牛云 bucket 名称
     */
    private String bucketName;

}