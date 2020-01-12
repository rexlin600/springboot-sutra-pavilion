package xyz.rexlin600.rabbitmq.pattern.simple.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.simple.config.SimpleConfig;

import java.time.Instant;

/**
 * Simple 消费者
 * 简单模式
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Slf4j
@Component
@RabbitListener(queues = SimpleConfig.SIMPLE_QUEUE)
public class SimpleCustomer {

    /**
     * 处理消息 String
     *
     * @param message
     */
    @SneakyThrows
    @RabbitHandler
    public void handlerSimple(String content, Channel channel, Message message) {
        long milli = Instant.now().toEpochMilli();
        log.info("==>  Simple consume content=[{}] at [{}]", content, milli);
        // true表示一次确认所有小于tag的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}