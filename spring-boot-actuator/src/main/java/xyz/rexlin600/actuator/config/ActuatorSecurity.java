package xyz.rexlin600.actuator.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Actuator security
 *
 * @author hekunlin
 */
@Configuration
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

	/**
	 * Configure *
	 *
	 * @param http http
	 * @throws Exception exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
				.anyRequest().hasRole("admin")
				.and()
				.httpBasic();
	}
}