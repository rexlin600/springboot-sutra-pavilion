package xyz.rexlin600.custom.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.rexlin600.starter.dds.annotation.EnableDynamicDataSource;

/**
 * Custom test starter application
 *
 * @author hekunlin
 */
@MapperScan(value = "xyz.rexlin600.custom.test.mapper")
@EnableDynamicDataSource
@SpringBootApplication(scanBasePackages = {
		"xyz.rexlin600"
})
public class CustomTestStarterApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustomTestStarterApplication.class, args);
	}

}