package xyz.rexlin600.aop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: rexlin600
 * @date: 2020-02-12
 */
@SpringBootApplication
@MapperScan(value = "xyz.rexlin600.aop.mapper")
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

}