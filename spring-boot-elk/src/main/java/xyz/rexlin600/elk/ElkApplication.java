package xyz.rexlin600.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The type Elk application.
 *
 * @author rexlin600
 */
@SpringBootApplication
@EnableScheduling
public class ElkApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ElkApplication.class, args);
	}


}
