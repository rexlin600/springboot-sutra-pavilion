package xyz.rexlin600.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Kafka application
 *
 * @author hekunlin
 */
@SpringBootApplication
public class KafkaApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

}