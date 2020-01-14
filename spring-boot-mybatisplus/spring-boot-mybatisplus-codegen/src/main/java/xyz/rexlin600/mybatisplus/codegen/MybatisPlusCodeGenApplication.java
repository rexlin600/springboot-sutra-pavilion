package xyz.rexlin600.mybatisplus.codegen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * MybatisPlusCodeGenApplication 启动类
 *
 * @author: hekunlin
 * @date: 2020/1/14
 */
@EnableConfigurationProperties
@MapperScan(basePackages = {"xyz.rexlin600.mybatisplus.codegen.mapper"})
@SpringBootApplication
public class MybatisPlusCodeGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusCodeGenApplication.class, args);
    }

}