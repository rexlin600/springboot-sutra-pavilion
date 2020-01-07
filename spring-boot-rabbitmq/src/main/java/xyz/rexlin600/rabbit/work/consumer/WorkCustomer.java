package xyz.rexlin600.rabbit.work.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbit.simple.config.SimpleConfig;
import xyz.rexlin600.rabbit.work.config.WorkConfig;

/**
 * Work 消费者
 * Work模式
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Slf4j
@Component
@RabbitListener(queues = WorkConfig.WORK_QUEUE)
public class WorkCustomer {

    /**
     * 处理消息 String
     *
     * @param message
     */
    @SneakyThrows
    @RabbitHandler
    public void handlerOne(String content, Channel channel, Message message) {
        log.info("==>  Work one consume message=[{}] and content=[{}]", message, content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);   // true表示一次确认所有小于tag的消息
    }

    /**
     * 处理消息 String
     *
     * @param message
     */
    @SneakyThrows
    @RabbitHandler
    public void handlerTwo(String content, Channel channel, Message message) {
        log.info("==>  Work two consume message=[{}] and content=[{}]", message, content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);   // true表示一次确认所有小于tag的消息
    }

}