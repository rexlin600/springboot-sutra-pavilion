package xyz.rexlin600.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import xyz.rexlin600.kafka.constant.KafkaTopicConstant;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * 同步消息生产者
 *
 * @author: hekunlin
 * @date: 2020/7/2
 */
@Slf4j
@Component
public class SyncProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 同步发送消息
     */
    public SendResult sendSyncMessage(String msg) throws ExecutionException, InterruptedException {
        // 构建你的消息内容（这里就发送简单字符串） ...

        // 发送消息
        return kafkaTemplate.send(KafkaTopicConstant.TOPIC, msg).get();
    }


}