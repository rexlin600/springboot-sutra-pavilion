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
 * Async producer
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class AsyncProducer {

	/**
	 * Kafka template
	 */
	@Resource
	private KafkaTemplate<String, Object> kafkaTemplate;

	/**
	 * Send message listenable future
	 *
	 * @param msg msg
	 * @return the listenable future
	 */
	public ListenableFuture<SendResult<String, Object>> sendMessage(String msg) {
		log.info("==>  ASYNC send message at {}", Instant.now().toEpochMilli());
		// 构建你的消息内容（这里就发送简单字符串） ...

		// 发送消息
		return kafkaTemplate.send(KafkaTopicConstant.ASYNC_TOPIC, msg);
	}


}