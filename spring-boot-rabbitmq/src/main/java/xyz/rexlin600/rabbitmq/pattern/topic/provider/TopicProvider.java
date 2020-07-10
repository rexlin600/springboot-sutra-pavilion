package xyz.rexlin600.rabbitmq.pattern.topic.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.topic.config.TopicConfig;

import java.time.Instant;

/**
 * Topic provider
 *
 * @author hekunlin
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class TopicProvider {

	/**
	 * Amqp template
	 */
	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * Topic product str 1
	 */
	@SneakyThrows
	public void topicProductStr1() {
		long milli = Instant.now().toEpochMilli();
		String content = "Topic product at " + TopicConfig.TOPIC_QUEUE_A + " config " + milli;
		log.info("==> " + content + " to queue=[{}] and at [{}]", TopicConfig.TOPIC_QUEUE_A, milli);
		amqpTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, TopicConfig.TOPIC_ROUTINGKEY_A, content);
	}


	/**
	 * Topic product str 2
	 */
	@SneakyThrows
	public void topicProductStr2() {
		long milli = Instant.now().toEpochMilli();
		String content = "Topic product at " + TopicConfig.TOPIC_QUEUE_ALL + " config " + milli;
		log.info("==> " + content + " to queue=[{}] and at [{}]", TopicConfig.TOPIC_QUEUE_ALL, milli);
		amqpTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, TopicConfig.TOPIC_ROUTINGKEY_ALL, content);
	}


	/**
	 * Topic product str 3
	 */
	@SneakyThrows
	public void topicProductStr3() {
		long milli = Instant.now().toEpochMilli();
		String content = "Topic product at " + TopicConfig.TOPIC_QUEUE_ALL + " config " + milli;
		log.info("==> " + content + " to queue=[{}] and at [{}]", TopicConfig.TOPIC_QUEUE_ALL, milli);
		amqpTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, "routingKey.rexlin600.config.1", content);
	}


}