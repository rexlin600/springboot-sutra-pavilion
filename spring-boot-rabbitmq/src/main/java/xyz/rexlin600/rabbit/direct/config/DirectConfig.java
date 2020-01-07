package xyz.rexlin600.rabbit.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct 配置类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Configuration
public class DirectConfig {

    /**
     * 直连queue、routingKey、exchange
     */
    public static final String DIRECT_QUEUE = "queue.rexlin600.direct";
    public static final String DIRECT_ROUTINGKEY = "routingKey.rexlin600.direct";
    public static final String DIRECT_EXCHANGE = "exchange.rexlin600.direct";


    /**
     * Queue
     *
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE);
    }

    /**
     * Exchange
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    /**
     * Binding
     *
     * @param directQueue
     * @param directExchange
     * @return
     */
    @Bean
    Binding bindingDirectExchangeMessage(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue)
                .to(directExchange)
                .with(DIRECT_ROUTINGKEY);
    }

}