package xyz.rexlin600.custom.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.rexlin600.starter.dds.annotation.EnableDynamicDataSource;

/**
 * 自定义starter测试服务启动类
 *
 * @author: hekunlin
 * @since: 2020/3/16
 */
@MapperScan(value = "xyz.rexlin600.custom.test.mapper")
@EnableDynamicDataSource
@SpringBootApplication(scanBasePackages = {
        "xyz.rexlin600"
})
public class CustomTestStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomTestStarterApplication.class, args);
    }

}