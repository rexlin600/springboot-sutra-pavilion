package xyz.rexlin600.rabbitmq.pattern.simple.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Simple 配置类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Configuration
public class SimpleConfig {

    /**
     * 简单模式
     */
    public static final String SIMPLE_QUEUE = "queue.rexlin600.simple";


    /**
     * Queue
     *
     * @return
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue(SIMPLE_QUEUE);
    }

}