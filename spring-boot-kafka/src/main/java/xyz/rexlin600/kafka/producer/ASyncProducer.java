package xyz.rexlin600.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import xyz.rexlin600.kafka.constant.KafkaTopicConstant;

import javax.annotation.Resource;
import java.time.Instant;

/**
 * 异步消息生产者
 *
 * @author: hekunlin
 * @date: 2020/7/2
 */
@Slf4j
@Component
public class ASyncProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 异步发送消息
     *
     * @param msg
     * @return
     */
    public ListenableFuture<SendResult<String, Object>> sendMessage(String msg) {
        log.info("==>  ASYNC send message at {}", Instant.now().toEpochMilli());
        // 构建你的消息内容（这里就发送简单字符串） ...

        // 发送消息
        return kafkaTemplate.send(KafkaTopicConstant.ASYNC_TOPIC, msg);
    }


}