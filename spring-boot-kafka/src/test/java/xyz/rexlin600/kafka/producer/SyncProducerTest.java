package xyz.rexlin600.kafka.producer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.kafka.KafkaApplication;

import java.util.concurrent.CountDownLatch;

/**
 * @description
 * @auther hekunlin
 * @create 2020-07-02 16:35
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class SyncProducerTest {

    @Autowired
    private SyncProducer syncProducer;

    /**
     * 发送同步消息、手动提交
     */
    @SneakyThrows
    @Test
    public void testSync() {
        String content = "Sync Hello Kafka";
        SendResult sendResult = syncProducer.sendSyncMessage(content);
        log.info("[testSync]发送结果：[{}]]", sendResult);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}