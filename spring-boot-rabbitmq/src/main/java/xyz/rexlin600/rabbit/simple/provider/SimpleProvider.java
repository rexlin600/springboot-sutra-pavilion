package xyz.rexlin600.rabbit.simple.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbit.simple.config.SimpleConfig;

import java.time.Instant;

/**
 * Simple 生产者
 * 简单模式
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Slf4j
@Component
public class SimpleProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 生产简单字符串消息
     */
    @SneakyThrows
    public void simpleProductStr() {
        long milli = Instant.now().toEpochMilli();
        String content = "Simple product message at " + milli;
        log.info("==>  " + content + " to queue=[{}] and at [{}]", SimpleConfig.SIMPLE_QUEUE, milli);
        amqpTemplate.convertAndSend(SimpleConfig.SIMPLE_QUEUE, content);
    }

}