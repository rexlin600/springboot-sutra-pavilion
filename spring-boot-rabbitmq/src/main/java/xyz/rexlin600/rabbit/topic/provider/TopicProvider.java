package xyz.rexlin600.rabbit.topic.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbit.direct.config.DirectConfig;
import xyz.rexlin600.rabbit.topic.config.TopicConfig;

import java.time.Instant;

/**
 * Topic 生产者类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class TopicProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送到指定的topic {@link TopicConfig#TOPIC_ROUTINGKEY_A}
     */
    @SneakyThrows
    public void topicProductStr1() {
        long milli = Instant.now().toEpochMilli();
        String content = "Topic product at " + TopicConfig.TOPIC_QUEUE_A + " config " + milli;
        log.info("==> Topic product a message to queue=[{}] and at [{}]", TopicConfig.TOPIC_QUEUE_A, milli);
        amqpTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, TopicConfig.TOPIC_ROUTINGKEY_A, content);
    }


    /**
     * 发送到更大范围的topic 1 {@link TopicConfig#TOPIC_QUEUE_ALL}
     */
    @SneakyThrows
    public void topicProductStr2() {
        long milli = Instant.now().toEpochMilli();
        String content = "Topic product at " + TopicConfig.TOPIC_QUEUE_ALL + " config " + milli;
        log.info("==> Topic product a message to queue=[{}] and at [{}]", TopicConfig.TOPIC_QUEUE_ALL, milli);
        amqpTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, TopicConfig.TOPIC_ROUTINGKEY_ALL, content);
    }


    /**
     * 发送到更大范围的topic 2 {@link TopicConfig#TOPIC_QUEUE_ALL}
     */
    @SneakyThrows
    public void topicProductStr3() {
        long milli = Instant.now().toEpochMilli();
        String content = "Topic product at " + TopicConfig.TOPIC_QUEUE_ALL + " config " + milli;
        log.info("==> Topic product a message to queue=[{}] and at [{}]", TopicConfig.TOPIC_QUEUE_ALL, milli);
        amqpTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, "routingKey.rexlin600.config.1", content);
    }


}