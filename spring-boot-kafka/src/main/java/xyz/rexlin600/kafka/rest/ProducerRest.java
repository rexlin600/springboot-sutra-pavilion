package xyz.rexlin600.kafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.kafka.producer.AsyncProducer;
import xyz.rexlin600.kafka.producer.SyncProducer;

import java.time.Instant;

/**
 * Kafka 生产者接口
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/producer")
public class ProducerRest {

	/**
	 * Sync producer
	 */
	private final SyncProducer syncProducer;
	/**
	 * A sync producer
	 */
	private final AsyncProducer aSyncProducer;

	/**
	 * Producer rest
	 *
	 * @param syncProducer  sync producer
	 * @param aSyncProducer a sync producer
	 */
	@Autowired
	public ProducerRest(SyncProducer syncProducer, AsyncProducer aSyncProducer) {
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
	private void sendAsync() {
		aSyncProducer.sendMessage("I'm a ASync Message " + Instant.now().toEpochMilli());
	}


}