package xyz.rexlin600.openfeign.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * open feign 启动类
 *
 * @author: hekunlin
 * @date: 2020/5/7
 */
@EnableFeignClients(basePackages = "xyz.rexlin600")
@EnableEurekaClient
@SpringBootApplication
public class OpenFeignSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignSvcApplication.class, args);
    }

}