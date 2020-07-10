package xyz.rexlin600.kafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.kafka.producer.AsyncProducer;
import xyz.rexlin600.kafka.producer.SyncProducer;

import java.time.Instant;

/**
 * Producer rest
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
	 * Send sync
	 */
	@PostMapping("/sync/send")
	private void sendSync() {
		syncProducer.sendMessage("I'm a Sync Message " + Instant.now().toEpochMilli());
	}

	/**
	 * Send async
	 */
	@PostMapping("/async/send")
	private void sendAsync() {
		aSyncProducer.sendMessage("I'm a ASync Message " + Instant.now().toEpochMilli());
	}


}