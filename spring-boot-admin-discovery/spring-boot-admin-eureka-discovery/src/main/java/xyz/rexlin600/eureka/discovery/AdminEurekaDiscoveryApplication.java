package xyz.rexlin600.eureka.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Admin eureka discovery application
 *
 * @author hekunlin
 */
@EnableEurekaServer
@SpringBootApplication
public class AdminEurekaDiscoveryApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AdminEurekaDiscoveryApplication.class, args);
	}

	/**
	 * Web security config
	 */
	@EnableWebSecurity
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		/**
		 * Configure *
		 *
		 * @param http http
		 * @throws Exception exception
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			super.configure(http);//加这句是为了访问eureka控制台和/actuator时能做安全控制
			http.csrf().disable();//关闭csrf
		}
	}

}