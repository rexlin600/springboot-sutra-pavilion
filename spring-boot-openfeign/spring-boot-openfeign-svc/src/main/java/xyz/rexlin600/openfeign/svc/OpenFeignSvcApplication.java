package xyz.rexlin600.openfeign.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Open feign svc application
 *
 * @author hekunlin
 */
@EnableFeignClients(basePackages = "xyz.rexlin600")
@EnableEurekaClient
@SpringBootApplication
public class OpenFeignSvcApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OpenFeignSvcApplication.class, args);
	}

}