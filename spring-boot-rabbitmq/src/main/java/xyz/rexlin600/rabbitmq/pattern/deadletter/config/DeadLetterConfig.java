package xyz.rexlin600.rabbitmq.pattern.deadletter.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * DeadLetter 配置类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@SuppressWarnings("Duplicates")
@Configuration
public class DeadLetterConfig {


    /**
     * 死信队列流程：
     *
     * 生产者 -> 消息 -> 交换机（也可以是DLX交换机） -> 队列（相当于死信队列，满足条件会转发到监听队列） -> 变成死信 ->  DLX交换机 -> 队列（相当于监听队列，会被处理） -> 消费者
     */

    /**
     * x-dead-letter-exchange 声明死信队列Exchange
     * x-dead-letter-routing-key 声明死信队列抛出异常重定向队列的 routingKey，然后这个key会把消息导向到指定的 Queue
     */
    private static final String DL_EXCHANGE_HEADER = "x-dead-letter-exchange";
    private static final String DL_REDIRECT_ROUTING_KEY_HEADER = "x-dead-letter-routing-key";

    /**
     * 死信队列：queue、exchange、routingKey
     */
    public static final String DEAD_LETTER_QUEUE = "queue.rexlin600.dl.queue";
    public static final String REDIRECT_QUEUE = "queue.rexlin600.dl.redirect.queue";
    public static final String DL_EXCHANGE = "exchange.rexlin600.dl";
    public static final String DEAD_LETTER_ROUTING_KEY = "routingKey.rexlin600.dl";
    public static final String REDIRECT_ROUTING_KEY = "routingKey.rexlin600.dl.redirect";

    /**
     * Queue
     *
     * <p>
     * 死信队列
     *
     * @return
     */
    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put(DL_EXCHANGE_HEADER, DL_EXCHANGE);
        args.put(DL_REDIRECT_ROUTING_KEY_HEADER, REDIRECT_ROUTING_KEY);
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).withArguments(args).build();
    }

    /**
     * 死信队列转发队列
     *
     * @return
     */
    @Bean
    public Queue redirectQueue() {
        return QueueBuilder.durable(REDIRECT_QUEUE).build();
    }


    /**
     * Exchange
     *
     * <p>
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     *
     * @return
     */
    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange(DL_EXCHANGE).durable(true).build();
    }


    /**
     * Binding
     *
     * <p>
     * 死信队列绑定到死信交换器上
     *
     * @return the binding
     */
    @Bean
    public Binding deadLetterBinding() {
        return new Binding(DEAD_LETTER_QUEUE, Binding.DestinationType.QUEUE, DL_EXCHANGE, DEAD_LETTER_ROUTING_KEY, null);

    }

    /**
     * 将重定向队列通过routingKey绑定到死信队列的Exchange上
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding() {
        return new Binding(REDIRECT_QUEUE, Binding.DestinationType.QUEUE, DL_EXCHANGE, REDIRECT_ROUTING_KEY, null);
    }


}