package xyz.rexlin600.aop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Aop application
 *
 * @author hekunlin
 */
@EnableAsync
@EnableFeignClients
@SpringBootApplication
@MapperScan(value = "xyz.rexlin600.aop.mapper")
public class AopApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

}