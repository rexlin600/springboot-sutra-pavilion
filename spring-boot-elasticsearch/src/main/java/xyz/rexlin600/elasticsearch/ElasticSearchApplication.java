package xyz.rexlin600.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ES 启动类
 *
 * @author: hekunlin
 * @date: 2020/1/6
 */
@SpringBootApplication
public class ElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }

}