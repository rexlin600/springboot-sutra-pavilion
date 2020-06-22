package xyz.rexlin600.oss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.Serializable;

/**
 * @author: hekunlin
 * @date: 2020/6/21
 */
@Qualifier(value = "qnOssConfig")
@ConditionalOnProperty(prefix = OssConstant.PREFIX_QN, name = "enable", havingValue = "true")
@Data
@Configuration
@ConfigurationProperties(prefix = OssConstant.PREFIX_QN)
public class QnOssConfig implements Serializable {

    /**
     * 是否启用
     */
    private Boolean enable;

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