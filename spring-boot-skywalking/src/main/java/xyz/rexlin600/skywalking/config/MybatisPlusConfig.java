package xyz.rexlin600.skywalking.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis plus config
 *
 * @author hekunlin
 */
@Configuration
@MapperScan("xyz.rexlin600.mybatisplus.mapper")
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