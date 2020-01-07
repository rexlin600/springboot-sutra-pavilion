package xyz.rexlin600.rabbit.direct.provider;

import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbit.direct.config.DirectConfig;

import java.time.Instant;

/**
 * Direct 生产者类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Component
public class DirectProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 生产简单字符串消息
     */
    @SneakyThrows
    public void product() {
        long milli = Instant.now().toEpochMilli();
        String content = "HelloWorld AMQP-RabbitMQ " + milli;
        System.out.println("==>  producer product a message to queue=" + DirectConfig.DIRECT_QUEUE + " at=" + milli);
        amqpTemplate.convertAndSend(DirectConfig.DIRECT_QUEUE, content);
    }


}