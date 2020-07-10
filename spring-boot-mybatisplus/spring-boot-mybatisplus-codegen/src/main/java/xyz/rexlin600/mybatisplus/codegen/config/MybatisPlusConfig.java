package xyz.rexlin600.mybatisplus.codegen.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis plus config
 *
 * @author hekunlin
 */
@Configuration
public class MybatisPlusConfig {

	/**
	 * Pagination interceptor pagination interceptor
	 *
	 * @return the pagination interceptor
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

}