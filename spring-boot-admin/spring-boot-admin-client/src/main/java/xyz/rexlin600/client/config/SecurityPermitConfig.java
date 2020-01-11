package xyz.rexlin600.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: hekunlin
 * @date: 2020/1/8
 */
@Configuration
public class SecurityPermitConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.user.roles}")
    String roles;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf 忽略 actuator 的请求
        http.csrf().ignoringAntMatchers("/actuator/**");

        // 允许所有请求访问，不安全，所以改为下面必须登录为响应角色的用户才可以查看
        //http.authorizeRequests().anyRequest().permitAll()
        //        .and().csrf().disable();

        // 具有 role 的角色才允许访问，保护端点
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
                .anyRequest().hasRole(roles)
                .and()
                .httpBasic();
    }

}