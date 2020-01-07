package xyz.rexlin600.rabbit.direct.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class DirectProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 生产简单字符串消息
     */
    @SneakyThrows
    public void directProductStr() {
        long milli = Instant.now().toEpochMilli();
        String content = "Direct product message at " + milli;
        log.info("==> " + content + " to queue=[{}] and at [{}]", DirectConfig.DIRECT_QUEUE, milli);
        amqpTemplate.convertAndSend(DirectConfig.DIRECT_QUEUE, content);
    }


}