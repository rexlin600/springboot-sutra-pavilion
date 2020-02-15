package xyz.rexlin600.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * GitLabConfigBean 配置类
 *
 * @author: rexlin600
 * @date: 2020-02-14
 */
@ToString
@Data
@Component
@ConfigurationProperties(prefix = "gitlab")
public class GitLabConfigBean {

    /**
     * GitLab 地址
     */
    private String host;

    /**
     * GitLab access Token
     */
    private String token;

    /**
     * GitLab 用户名
     */
    private String username;

    /**
     * GitLab 密码
     */
    private String password;

    /**
     * GitLab api 命名空间
     */
    private String apiNamespace;

    /**
     * 克隆项目最大等待时间
     */
    private Long maxTime;

}