package xyz.rexlin600.rabbitmq.pattern.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct config
 *
 * @author hekunlin
 */
@Configuration
public class DirectConfig {

	/**
	 * DIRECT_QUEUE
	 */
	public static final String DIRECT_QUEUE = "queue.rexlin600.direct";
	/**
	 * DIRECT_ROUTINGKEY
	 */
	public static final String DIRECT_ROUTINGKEY = "routingKey.rexlin600.direct";
	/**
	 * DIRECT_EXCHANGE
	 */
	public static final String DIRECT_EXCHANGE = "exchange.rexlin600.direct";


	/**
	 * Direct queue queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue directQueue() {
		return new Queue(DIRECT_QUEUE);
	}

	/**
	 * Direct exchange direct exchange
	 *
	 * @return the direct exchange
	 */
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(DIRECT_EXCHANGE);
	}

	/**
	 * Binding direct exchange binding
	 *
	 * @param directQueue    direct queue
	 * @param directExchange direct exchange
	 * @return the binding
	 */
	@Bean
	public Binding bindingDirectExchange(Queue directQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(directQueue)
				.to(directExchange)
				.with(DIRECT_ROUTINGKEY);
	}

}