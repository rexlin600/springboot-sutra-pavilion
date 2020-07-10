package xyz.rexlin600.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Redis application
 *
 * @author hekunlin
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RedisApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}