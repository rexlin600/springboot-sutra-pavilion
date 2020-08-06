package xyz.rexlin600.easy.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Excel 应用程序
 *
 * @author hekunlin
 * @since 2020/8/6
 */
@MapperScan(value = {"xyz.rexlin600"})
@SpringBootApplication
public class ExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelApplication.class, args);
	}

}