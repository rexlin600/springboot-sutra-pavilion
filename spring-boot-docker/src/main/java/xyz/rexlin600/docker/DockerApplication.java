package xyz.rexlin600.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Docker application
 *
 * @author hekunlin
 */
@SpringBootApplication
public class DockerApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DockerApplication.class, args);
	}

}