package xyz.rexlin600.kafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.kafka.producer.ASyncProducer;
import xyz.rexlin600.kafka.producer.SyncProducer;

import java.time.Instant;

/**
 * 生产者 Rest 接口
 *
 * @author: hekunlin
 * @since: 2020/7/3
 */
@RestController
@RequestMapping("/producer")
public class ProducerRest {

    private final SyncProducer syncProducer;
    private final ASyncProducer aSyncProducer;

    @Autowired
    public ProducerRest(SyncProducer syncProducer, ASyncProducer aSyncProducer) {
        this.syncProducer = syncProducer;
        this.aSyncProducer = aSyncProducer;
    }


    /**
     * 发送同步消息
     */
    @PostMapping("/sync/send")
    private void sendSync() {
        syncProducer.sendMessage("I'm a Sync Message " + Instant.now().toEpochMilli());
    }

    /**
     * 发送异步消息
     */
    @PostMapping("/async/send")
    private void sendASync() {
        aSyncProducer.sendMessage("I'm a ASync Message " + Instant.now().toEpochMilli());
    }


}