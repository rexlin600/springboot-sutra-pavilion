package xyz.rexlin600.rabbitmq.pattern.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic config
 *
 * @author hekunlin
 */
@Configuration
public class TopicConfig {

	/**
	 * TOPIC_QUEUE_A
	 */
	public static final String TOPIC_QUEUE_A = "queue.rexlin600.config.a";
	/**
	 * TOPIC_QUEUE_ALL
	 */
	public static final String TOPIC_QUEUE_ALL = "queue.rexlin600.config.all";
	/**
	 * TOPIC_ROUTINGKEY_A
	 */
	public static final String TOPIC_ROUTINGKEY_A = "routingKey.rexlin600.config.a";
	/**
	 * TOPIC_ROUTINGKEY_ALL
	 */
	public static final String TOPIC_ROUTINGKEY_ALL = "routingKey.rexlin600.config.#";
	/**
	 * TOPIC_EXCHANGE
	 */
	public static final String TOPIC_EXCHANGE = "exchange.rexlin600.config";


	/**
	 * Topic queue a queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue topicQueueA() {
		return new Queue(TOPIC_QUEUE_A);
	}

	/**
	 * Topic queue all queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue topicQueueAll() {
		return new Queue(TOPIC_QUEUE_ALL);
	}

	/**
	 * Topic exchange topic exchange
	 *
	 * @return the topic exchange
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}

	/**
	 * Binding direct exchange a binding
	 *
	 * @param topicQueueA   topic queue a
	 * @param topicExchange topic exchange
	 * @return the binding
	 */
	@Bean
	public Binding bindingDirectExchangeA(Queue topicQueueA, TopicExchange topicExchange) {
		return BindingBuilder.bind(topicQueueA)
				.to(topicExchange)
				.with(TOPIC_ROUTINGKEY_A);
	}

	/**
	 * Binding direct exchange all binding
	 *
	 * @param topicQueueAll topic queue all
	 * @param topicExchange topic exchange
	 * @return the binding
	 */
	@Bean
	public Binding bindingDirectExchangeAll(Queue topicQueueAll, TopicExchange topicExchange) {
		return BindingBuilder.bind(topicQueueAll)
				.to(topicExchange)
				.with(TOPIC_ROUTINGKEY_ALL);
	}

}