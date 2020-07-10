package xyz.rexlin600.logback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Logback application
 *
 * @author hekunlin
 */
@SpringBootApplication
public class LogbackApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(LogbackApplication.class, args);
	}

}