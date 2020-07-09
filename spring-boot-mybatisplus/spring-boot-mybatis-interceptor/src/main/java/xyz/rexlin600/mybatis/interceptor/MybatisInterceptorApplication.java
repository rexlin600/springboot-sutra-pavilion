package xyz.rexlin600.mybatis.interceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Mybatis 自定义拦截器启动类
 *
 * @author: hekunlin
 * @since: 2020/3/24
 */
@MapperScan("xyz.rexlin600.mybatis.interceptor.mapper")
@SpringBootApplication
public class MybatisInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisInterceptorApplication.class, args);
    }

}