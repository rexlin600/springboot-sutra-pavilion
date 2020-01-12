package xyz.rexlin600.rabbitmq.pattern.topic.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic 配置类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Configuration
public class TopicConfig {

    /**
     * TOPIC模式：queue、routingKey、exchange
     */
    public static final String TOPIC_QUEUE_A = "queue.rexlin600.config.a";
    public static final String TOPIC_QUEUE_ALL = "queue.rexlin600.config.all";
    public static final String TOPIC_ROUTINGKEY_A = "routingKey.rexlin600.config.a";
    public static final String TOPIC_ROUTINGKEY_ALL = "routingKey.rexlin600.config.#";
    public static final String TOPIC_EXCHANGE = "exchange.rexlin600.config";


    /**
     * Queue
     *
     * @return
     */
    @Bean
    public Queue topicQueueA() {
        return new Queue(TOPIC_QUEUE_A);
    }

    @Bean
    public Queue topicQueueAll() {
        return new Queue(TOPIC_QUEUE_ALL);
    }

    /**
     * Exchange
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * Binding
     *
     * @param topicQueueA
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingDirectExchangeA(Queue topicQueueA, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueA)
                .to(topicExchange)
                .with(TOPIC_ROUTINGKEY_A);
    }

    @Bean
    Binding bindingDirectExchangeAll(Queue topicQueueAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueAll)
                .to(topicExchange)
                .with(TOPIC_ROUTINGKEY_ALL);
    }

}