package xyz.rexlin600.client.discovery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Admin Client 启动类
 *
 * @author: hekunlin
 * @date: 2020/1/8
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AdminClientDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminClientDiscoveryApplication.class, args);
    }

}