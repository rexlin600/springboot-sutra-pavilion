package xyz.rexlin600.openfeign.svc.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign configuration
 *
 * @author hekunlin
 */
@Configuration
public class FeignConfiguration {

	/**
	 * Feign logger level logger . level
	 *
	 * @return the logger . level
	 */
	@Bean
	Logger.Level feignLoggerLevel() {
		//这里记录所有，根据实际情况选择合适的日志level
		return Logger.Level.FULL;
	}

}