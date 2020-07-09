package xyz.rexlin600.rabbitmq.pattern.fanout.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.fanout.config.FanoutConfig;

import java.time.Instant;

/**
 * Fanout 生产者类
 *
 * @author: hekunlin
 * @since: 2020/1/7
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class FanoutProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 生产简单字符串消息
     */
    public void fanoutProductStr() {
        long milli = Instant.now().toEpochMilli();
        String content = "Fanout message at " + milli;
        log.info("==>  " + content + " to queues and at [{}]", milli);
        // 广播
        amqpTemplate.convertAndSend(FanoutConfig.FANOUT_EXCHANGE, "", content);
    }

}