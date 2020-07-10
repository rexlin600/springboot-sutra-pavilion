package xyz.rexlin600.mybatis.interceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Mybatis interceptor application
 *
 * @author hekunlin
 */
@MapperScan("xyz.rexlin600.mybatis.interceptor.mapper")
@SpringBootApplication
public class MybatisInterceptorApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MybatisInterceptorApplication.class, args);
	}

}