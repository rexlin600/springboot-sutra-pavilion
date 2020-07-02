package xyz.rexlin600.kafka.producer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import xyz.rexlin600.kafka.KafkaApplication;

import java.util.concurrent.CountDownLatch;

/**
 * @description
 * @auther hekunlin
 * @create 2020-07-02 17:41
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class ASyncProducerTest {

    @Autowired
    private ASyncProducer asyncProducer;

    /**
     * 发送异步消息、手动提交
     */
    @SneakyThrows
    @Test
    public void testASync() {
        String content = "ASync Hello Kafka";
        ListenableFuture<SendResult<String, Object>> future = asyncProducer.sendAsyncMessage(content);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("[testASync]发送异常：[{}]]", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("[testSync]发送结果：[{}]]", result);
            }
        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}