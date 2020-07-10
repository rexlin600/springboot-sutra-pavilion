package xyz.rexlin600.skywalking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Skywalking application
 *
 * @author hekunlin
 */
@MapperScan(value = "xyz.rexlin600.skywalking.mapper")
@SpringBootApplication
public class SkywalkingApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SkywalkingApplication.class, args);
	}

}