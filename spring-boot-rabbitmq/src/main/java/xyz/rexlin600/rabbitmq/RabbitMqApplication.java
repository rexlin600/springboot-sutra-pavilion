package xyz.rexlin600.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Rabbit mq application
 *
 * @author hekunlin
 */
@SpringBootApplication
public class RabbitMqApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RabbitMqApplication.class, args);
	}

}