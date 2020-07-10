package xyz.rexlin600.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Oss application
 *
 * @author hekunlin
 */
@EnableConfigurationProperties
@SpringBootApplication
public class OssApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OssApplication.class, args);
	}

}