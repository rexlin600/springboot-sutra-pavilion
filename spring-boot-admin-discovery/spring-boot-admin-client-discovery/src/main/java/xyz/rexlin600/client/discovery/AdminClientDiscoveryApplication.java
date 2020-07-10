package xyz.rexlin600.client.discovery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Admin client discovery application
 *
 * @author hekunlin
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AdminClientDiscoveryApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AdminClientDiscoveryApplication.class, args);
	}

}