package xyz.rexlin600.aop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author: rexlin600
 * @since: 2020-02-12
 */
@EnableAsync
@EnableFeignClients
@SpringBootApplication
@MapperScan(value = "xyz.rexlin600.aop.mapper")
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

}