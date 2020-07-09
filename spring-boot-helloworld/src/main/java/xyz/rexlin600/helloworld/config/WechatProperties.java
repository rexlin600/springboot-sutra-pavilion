package xyz.rexlin600.helloworld.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信配置类
 *
 * @author: rexlin600
 * @since: 2020-02-18
 */
@Data
@ToString
@Component
@ConfigurationProperties("rexlin600.wechat")
public class WechatProperties {

    private Map<String, String> map;

}