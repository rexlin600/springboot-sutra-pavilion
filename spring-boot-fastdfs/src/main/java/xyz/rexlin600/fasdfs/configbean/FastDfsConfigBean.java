package xyz.rexlin600.fasdfs.configbean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * fastdfs 配置类
 *
 * @author: hekunlin
 * @date: 2020/5/8
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "biz.fastdfs")
public class FastDfsConfigBean {

    /**
     * 文件访问地址
     */
    private String httpUrl;

}