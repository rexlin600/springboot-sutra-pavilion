package xyz.rexlin600.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 启动类
 *
 * @author: hekunlin
 * @since: 2020/6/21
 */
@EnableConfigurationProperties
@SpringBootApplication
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }

}