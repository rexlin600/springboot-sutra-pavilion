package xyz.rexlin600.mybatisplus.codegen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Mybatis plus code gen application
 *
 * @author hekunlin
 */
@EnableConfigurationProperties
@MapperScan(basePackages = {"xyz.rexlin600.mybatisplus.codegen.mapper"})
@SpringBootApplication
public class MybatisPlusCodeGenApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusCodeGenApplication.class, args);
	}

}