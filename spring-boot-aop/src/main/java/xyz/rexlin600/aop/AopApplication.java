package xyz.rexlin600.aop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import xyz.rexlin600.aop.aspect.one.SysLogAspect;
import xyz.rexlin600.aop.aspect.two.feign.RemoteSysLogService;

/**
 * @author: rexlin600
 * @date: 2020-02-12
 */
@EnableFeignClients
@SpringBootApplication
@MapperScan(value = "xyz.rexlin600.aop.mapper")
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

}