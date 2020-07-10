package xyz.rexlin600.mybatisplus.crud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * My batis plus crud application
 *
 * @author hekunlin
 */
@MapperScan(value = {"xyz.rexlin600"})
@SpringBootApplication
public class MyBatisPlusCrudApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MyBatisPlusCrudApplication.class, args);
	}
}
