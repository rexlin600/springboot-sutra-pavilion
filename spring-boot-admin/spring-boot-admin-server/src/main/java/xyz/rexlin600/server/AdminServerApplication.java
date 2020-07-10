package xyz.rexlin600.server;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Admin server application
 *
 * @author hekunlin
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class AdminServerApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}

	/**
	 * Security secure config
	 */
	@SuppressWarnings("Duplicates")
	@Configuration
	public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
		/**
		 * Admin context path
		 */
		private final String adminContextPath;

		/**
		 * Security secure config
		 *
		 * @param adminServerProperties admin server properties
		 */
		public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
			this.adminContextPath = adminServerProperties.getContextPath();
		}

		/**
		 * Configure *
		 *
		 * @param http http
		 * @throws Exception exception
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
			successHandler.setTargetUrlParameter("redirectTo");
			successHandler.setDefaultTargetUrl(adminContextPath + "/");

			http.authorizeRequests()
					.antMatchers(adminContextPath + "/assets/**").permitAll()
					.antMatchers(adminContextPath + "/login").permitAll()
					.anyRequest().authenticated()
					.and()
					.formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
					.logout().logoutUrl(adminContextPath + "/logout").and()
					.httpBasic().and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					.ignoringAntMatchers(
							adminContextPath + "/instances",
							adminContextPath + "/actuator/**"
					);
			// @formatter:on
		}
	}

}