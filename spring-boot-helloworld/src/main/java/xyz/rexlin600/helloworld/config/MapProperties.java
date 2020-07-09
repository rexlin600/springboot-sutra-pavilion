package xyz.rexlin600.helloworld.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * MapProperties 类
 *
 * @author: hekunlin
 * @since: 2020/1/10
 */
@Data
@ToString
@Component
@ConfigurationProperties("rexlin600.kv")
public class MapProperties {

    private Map<String, Object> map;

}