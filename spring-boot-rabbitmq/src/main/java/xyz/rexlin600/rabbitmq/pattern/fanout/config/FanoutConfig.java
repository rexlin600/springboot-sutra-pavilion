package xyz.rexlin600.rabbitmq.pattern.fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout config
 *
 * @author hekunlin
 */
@Configuration
public class FanoutConfig {

	/**
	 * FANOUT_QUEUE_A
	 */
	public static final String FANOUT_QUEUE_A = "queue.rexlin600.fanout.a";
	/**
	 * FANOUT_QUEUE_B
	 */
	public static final String FANOUT_QUEUE_B = "queue.rexlin600.fanout.b";
	/**
	 * FANOUT_QUEUE_C
	 */
	public static final String FANOUT_QUEUE_C = "queue.rexlin600.fanout.c";
	/**
	 * FANOUT_EXCHANGE
	 */
	public static final String FANOUT_EXCHANGE = "exchange.rexlin600.fanout";

	/**
	 * Fan out a queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue fanOutA() {
		return new Queue(FANOUT_QUEUE_A);
	}

	/**
	 * Fan out b queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue fanOutB() {
		return new Queue(FANOUT_QUEUE_B);
	}

	/**
	 * Fan out c queue
	 *
	 * @return the queue
	 */
	@Bean
	public Queue fanOutC() {
		return new Queue(FANOUT_QUEUE_C);
	}

	/**
	 * Fanout exchange fanout exchange
	 *
	 * @return the fanout exchange
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}


	/**
	 * Binding fanout exchange a binding
	 *
	 * @param fanOutA        fan out a
	 * @param fanoutExchange fanout exchange
	 * @return the binding
	 */
	@Bean
	public Binding bindingFanoutExchangeA(Queue fanOutA, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutA).to(fanoutExchange);
	}

	/**
	 * Binding fanout exchange b binding
	 *
	 * @param fanOutB        fan out b
	 * @param fanoutExchange fanout exchange
	 * @return the binding
	 */
	@Bean
	public Binding bindingFanoutExchangeB(Queue fanOutB, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutB).to(fanoutExchange);
	}

	/**
	 * Binding fanout exchange c binding
	 *
	 * @param fanOutC        fan out c
	 * @param fanoutExchange fanout exchange
	 * @return the binding
	 */
	@Bean
	public Binding bindingFanoutExchangeC(Queue fanOutC, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutC).to(fanoutExchange);
	}


}