package xyz.rexlin600.rabbit.dl.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbit.direct.config.DirectConfig;
import xyz.rexlin600.rabbit.dl.config.DeadLetterConfig;

import java.time.Instant;
import java.util.UUID;

/**
 * DeadLetterProvider 提供者类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class DeadLetterProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 生产简单字符串消息
     *
     * <p>
     * 10S 过期 DeadLetter
     */
    @SneakyThrows
    public void productDLExpireStr() {
        long milli = Instant.now().toEpochMilli();
        String content = "DeadLetter product AMQP-RabbitMQ " + milli;

        log.info("==> DeadLetter product a message to queue=[{}] and at [{}]", DeadLetterConfig.DEAD_LETTER_QUEUE, milli);

        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setContentEncoding("UTF-8");
            messageProperties.setExpiration("10000");   // 10s expire
            return message;
        };

        // 注意 API 变化
        amqpTemplate.convertAndSend(DeadLetterConfig.DL_EXCHANGE, DeadLetterConfig.DEAD_LETTER_ROUTING_KEY, content, messagePostProcessor);
    }


}