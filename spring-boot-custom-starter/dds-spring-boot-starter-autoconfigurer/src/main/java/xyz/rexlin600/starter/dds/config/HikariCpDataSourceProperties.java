package xyz.rexlin600.starter.dds.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * HikariCP数据源
 *
 * @author: rexlin600
 * @since: 2020-03-07
 */
@Data
@Component
@ConfigurationProperties("spring.datasource")
public class HikariCpDataSourceProperties {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库链接
     */
    private String url;
    
    /**
     * 数据库驱动
     */
    private String driverClassName;

}