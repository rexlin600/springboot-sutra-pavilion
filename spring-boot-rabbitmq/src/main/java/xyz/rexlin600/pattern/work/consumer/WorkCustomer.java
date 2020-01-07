package xyz.rexlin600.pattern.work.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.rexlin600.pattern.work.config.WorkConfig;

import java.time.Instant;

/**
 * Work 消费者
 * Work模式
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Slf4j
@Component
public class WorkCustomer {

    /**
     * 处理消息 String
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = {
            @Queue(WorkConfig.WORK_QUEUE)
    })
    @SneakyThrows
    @RabbitHandler
    public void handlerWorkOne(String content, Channel channel, Message message) {
        long milli = Instant.now().toEpochMilli();
        log.info("==>  Work one consume content=[{}] at [{}]", content, milli);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);   // true表示一次确认所有小于tag的消息
    }

    /**
     * 处理消息 String
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = {
            @Queue(WorkConfig.WORK_QUEUE)
    })
    @SneakyThrows
    @RabbitHandler
    public void handlerWorkTwo(String content, Channel channel, Message message) {
        long milli = Instant.now().toEpochMilli();
        log.info("==>  Work two consume content=[{}] at [{}]", content, milli);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);   // true表示一次确认所有小于tag的消息
    }

}