package xyz.rexlin600.eureka.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SpringAdminEureka 启动类
 *
 * @author: hekunlin
 * @since: 2020/1/8
 */
@EnableEurekaServer
@SpringBootApplication
public class AdminEurekaDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminEurekaDiscoveryApplication.class, args);
    }

    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            super.configure(http);//加这句是为了访问eureka控制台和/actuator时能做安全控制
            http.csrf().disable();//关闭csrf
        }
    }

}