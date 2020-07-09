package xyz.rexlin600.eureka.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * SpringAdminEureka 启动类
 *
 * @author: hekunlin
 * @since: 2020/1/8
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoveryApplication.class, args);
    }

}