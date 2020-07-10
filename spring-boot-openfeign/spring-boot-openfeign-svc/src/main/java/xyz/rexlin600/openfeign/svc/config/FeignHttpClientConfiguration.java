package xyz.rexlin600.openfeign.svc.config;

import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Feign http client configuration
 *
 * @author hekunlin
 */
@Import(FeignAutoConfiguration.class)
@Configuration
public class FeignHttpClientConfiguration {

	// 启用 apache HttpClient 连接池

}