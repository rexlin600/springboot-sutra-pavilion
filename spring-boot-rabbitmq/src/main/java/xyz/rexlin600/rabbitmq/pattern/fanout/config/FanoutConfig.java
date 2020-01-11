package xyz.rexlin600.rabbitmq.pattern.fanout.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FanOut配置类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Configuration
public class FanoutConfig {

    /**
     * 广播：三个队列 a b c 绑定到 fanout exchange 上
     */
    public static final String FANOUT_QUEUE_A = "queue.rexlin600.fanout.a";
    public static final String FANOUT_QUEUE_B = "queue.rexlin600.fanout.b";
    public static final String FANOUT_QUEUE_C = "queue.rexlin600.fanout.c";
    public static final String FANOUT_EXCHANGE = "exchange.rexlin600.fanout";

    /**
     * Queue A、B、C
     *
     * @return
     */
    @Bean
    public Queue fanOutA() {
        return new Queue(FANOUT_QUEUE_A);
    }

    @Bean
    public Queue fanOutB() {
        return new Queue(FANOUT_QUEUE_B);
    }

    @Bean
    public Queue fanOutC() {
        return new Queue(FANOUT_QUEUE_C);
    }

    /**
     * Exchange
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }


    /**
     * Biding
     *
     * @param fanOutA
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingFanoutExchangeA(Queue fanOutA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutA).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutExchangeB(Queue fanOutB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutB).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutExchangeC(Queue fanOutC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutC).to(fanoutExchange);
    }


}