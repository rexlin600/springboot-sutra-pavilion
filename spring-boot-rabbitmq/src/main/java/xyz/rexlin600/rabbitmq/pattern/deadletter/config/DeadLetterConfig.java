package xyz.rexlin600.rabbitmq.pattern.deadletter.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Dead letter config
 *
 * @author hekunlin
 */
@SuppressWarnings("Duplicates")
@Configuration
public class DeadLetterConfig {

	/**
	 * DEAD_LETTER_QUEUE
	 */
	public static final String DEAD_LETTER_QUEUE = "queue.rexlin600.dl.queue";
	/**
	 * REDIRECT_QUEUE
	 */
	public static final String REDIRECT_QUEUE = "queue.rexlin600.dl.redirect.queue";
	/**
	 * DL_EXCHANGE
	 */
	public static final String DL_EXCHANGE = "exchange.rexlin600.dl";
	/**
	 * DEAD_LETTER_ROUTING_KEY
	 */
	public static final String DEAD_LETTER_ROUTING_KEY = "routingKey.rexlin600.dl";
	/**
	 * REDIRECT_ROUTING_KEY
	 */
	public static final String REDIRECT_ROUTING_KEY = "routingKey.rexlin600.dl.redirect";
	/**
	 * DL_EXCHANGE_HEADER
	 */
	private static final String DL_EXCHANGE_HEADER = "x-dead-letter-exchange";
	/**
	 * DL_REDIRECT_ROUTING_KEY_HEADER
	 */
	private static final String DL_REDIRECT_ROUTING_KEY_HEADER = "x-dead-letter-routing-key";

	/**
	 * Dead letter queue queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue deadLetterQueue() {
		Map<String, Object> args = new HashMap<>(2);
		args.put(DL_EXCHANGE_HEADER, DL_EXCHANGE);
		args.put(DL_REDIRECT_ROUTING_KEY_HEADER, REDIRECT_ROUTING_KEY);
		return QueueBuilder.durable(DEAD_LETTER_QUEUE).withArguments(args).build();
	}

	/**
	 * Redirect queue queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue redirectQueue() {
		return QueueBuilder.durable(REDIRECT_QUEUE).build();
	}


	/**
	 * Dead letter exchange exchange
	 *
	 * @return the exchange
	 */
	@Bean
	public Exchange deadLetterExchange() {
		return ExchangeBuilder.directExchange(DL_EXCHANGE).durable(true).build();
	}


	/**
	 * Dead letter binding binding
	 *
	 * @return the binding
	 */
	@Bean
	public Binding deadLetterBinding() {
		return new Binding(DEAD_LETTER_QUEUE, Binding.DestinationType.QUEUE, DL_EXCHANGE, DEAD_LETTER_ROUTING_KEY, null);

	}

	/**
	 * Redirect binding binding
	 *
	 * @return the binding
	 */
	@Bean
	public Binding redirectBinding() {
		return new Binding(REDIRECT_QUEUE, Binding.DestinationType.QUEUE, DL_EXCHANGE, REDIRECT_ROUTING_KEY, null);
	}


}