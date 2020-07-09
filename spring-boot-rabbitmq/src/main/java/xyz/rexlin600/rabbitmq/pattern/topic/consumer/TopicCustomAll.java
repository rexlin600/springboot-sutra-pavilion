package xyz.rexlin600.rabbitmq.pattern.topic.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.topic.config.TopicConfig;

import java.time.Instant;

/**
 * Topic 消费者类ALL
 *
 * @author: hekunlin
 * @since: 2020/1/7
 */
@Slf4j
@Component
@RabbitListener(queues = TopicConfig.TOPIC_QUEUE_ALL)
public class TopicCustomAll {

    /**
     * 处理消息 String
     *
     * @param message
     */
    @SneakyThrows
    @RabbitHandler
    public void handlerTopicAll(String content, Channel channel, Message message) {
        long milli = Instant.now().toEpochMilli();
        log.info("==>  Topic ALL consume content=[{}] at [{}]", content, milli);
        // true表示一次确认所有小于tag的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}