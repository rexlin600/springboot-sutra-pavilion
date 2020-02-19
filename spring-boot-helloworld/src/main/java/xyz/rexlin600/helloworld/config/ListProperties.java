package xyz.rexlin600.helloworld.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import xyz.rexlin600.helloworld.entity.AliApp;

import java.util.List;

/**
 * CollectionProperties 类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@ToString
@Data
@Component
@ConfigurationProperties(prefix = "rexlin600.list")
public class ListProperties {

    private List<String> list;

    private List<Integer> integerList;

    private List<Double> doubleList;

    private List<AliApp> aliAppList;

}