package xyz.rexlin600.rabbitmq.pattern.work.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Work 配置类
 *
 * @author: hekunlin
 * @since: 2020/1/7
 */
@Configuration
public class WorkConfig {

    /**
     * Work模式
     */
    public static final String WORK_QUEUE = "queue.rexlin600.work";


    /**
     * Queue
     *
     * @return
     */
    @Bean
    public Queue workQueue() {
        return new Queue(WORK_QUEUE);
    }

}