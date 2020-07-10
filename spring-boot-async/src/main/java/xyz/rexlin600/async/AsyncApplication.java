package xyz.rexlin600.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Async application
 *
 * @author hekunlin
 */
@EnableAsync
@SpringBootApplication
public class AsyncApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AsyncApplication.class, args);
	}

}