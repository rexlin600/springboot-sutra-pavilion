package xyz.rexlin600.rabbitmq.pattern.work.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Work config
 *
 * @author hekunlin
 */
@Configuration
public class WorkConfig {

	/**
	 * WORK_QUEUE
	 */
	public static final String WORK_QUEUE = "queue.rexlin600.work";


	/**
	 * Work queue queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue workQueue() {
		return new Queue(WORK_QUEUE);
	}

}