package xyz.rexlin600.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * RedisApp 启动类
 *
 * @author: hekunlin
 * @date: 2020/1/6
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
// 防止报错（因为这里未集成数据库）：Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}