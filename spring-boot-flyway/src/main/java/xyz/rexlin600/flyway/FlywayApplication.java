package xyz.rexlin600.flyway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Flyway application
 *
 * @author hekunlin
 */
@SpringBootApplication
public class FlywayApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FlywayApplication.class, args);
	}

}