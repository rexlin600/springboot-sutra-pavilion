package xyz.rexlin600.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: hekunlin
 * @since: 2020/1/8
 */
@Configuration
public class SecurityPermitConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.user.roles}")
    private String roles;

    /**
     * configure
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf 忽略 actuator 的请求
        http.csrf().ignoringAntMatchers("/actuator/**");

        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
                // 具有 role 的角色才允许访问，保护端点
                .anyRequest().hasRole(roles)
                .and()
                .httpBasic();
    }

}