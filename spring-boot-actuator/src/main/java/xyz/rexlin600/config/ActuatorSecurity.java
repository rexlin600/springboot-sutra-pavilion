package xyz.rexlin600.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 保护 HTTP endpoints
 *
 * @author: hekunlin
 * @date: 2020/1/8
 */
@Configuration
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
                .anyRequest().hasRole("admin")
                .and()
                .httpBasic();
    }
}