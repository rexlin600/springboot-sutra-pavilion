package xyz.rexlin600.pattern.work.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.pattern.work.config.WorkConfig;

import java.time.Instant;

/**
 * Work 生产者
 * Work模式
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Slf4j
@Component
public class WorkProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 生产简单字符串消息
     */
    @SneakyThrows
    public void workProductStr() {
        long milli = Instant.now().toEpochMilli();
        String content = "Work product message at " + milli;
        log.info("==>  " + content + " to queue=[{}] and at [{}]", WorkConfig.WORK_QUEUE, milli);
        amqpTemplate.convertAndSend(WorkConfig.WORK_QUEUE, content);
    }

}