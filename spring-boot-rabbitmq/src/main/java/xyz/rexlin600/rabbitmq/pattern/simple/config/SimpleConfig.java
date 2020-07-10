package xyz.rexlin600.rabbitmq.pattern.simple.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Simple config
 *
 * @author hekunlin
 */
@Configuration
public class SimpleConfig {

	/**
	 * SIMPLE_QUEUE
	 */
	public static final String SIMPLE_QUEUE = "queue.rexlin600.simple";


	/**
	 * Simple queue queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue simpleQueue() {
		return new Queue(SIMPLE_QUEUE);
	}

}