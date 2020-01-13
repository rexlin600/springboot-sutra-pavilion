package xyz.rexlin600.helloworld.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * HelloWorldProperties 配置类
 * <p>
 * 需要实现动态配置刷新要增加 @RefreshScope
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@SuppressWarnings("ALL")
@ToString
@Data
@Component
@ConfigurationProperties(prefix = "rexlin600.simple.properties")
public class SimpleProperties {

    /**
     * 用户名
     */
    private String name;

    /**
     * 个人站点 URI
     */
    private String webUrl;

    /**
     * 随机字符串
     */
    private String randomStr;

    /**
     * 随机int
     */
    private String randomInt;

    /**
     * 随机Long
     */
    private String randomLong;

    /**
     * 随机int，在 1-9 之间
     */
    private String randomIntLimit;

    /**
     * 随机Long，在 10000-999999 之间
     */
    private String randomLongLimit;

}