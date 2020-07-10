package xyz.rexlin600.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import xyz.rexlin600.kafka.constant.KafkaTopicConstant;

import javax.annotation.Resource;
import java.time.Instant;

/**
 * Sync producer
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class SyncProducer {

	/**
	 * Kafka template
	 */
	@Resource
	private KafkaTemplate<String, Object> kafkaTemplate;

	/**
	 * Send message *
	 *
	 * @param msg msg
	 */
	public void sendMessage(String msg) {
		log.info("==>  SYNC send message at {}", Instant.now().toEpochMilli());
		// 构建你的消息内容（这里就发送简单字符串） ...

		// 发送消息
		// 非阻塞式
		kafkaTemplate.send(KafkaTopicConstant.SYNC_TOPIC, msg);

		//return kafkaTemplate.send(KafkaTopicConstant.TOPIC, msg).get(); // 阻塞式
	}


}