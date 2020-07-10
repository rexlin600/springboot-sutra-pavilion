package xyz.rexlin600.rabbitmq.pattern.deadletter.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.deadletter.config.DeadLetterConfig;

import java.time.Instant;

/**
 * Dead letter provider
 *
 * @author hekunlin
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class DeadLetterProvider {

	/**
	 * Amqp template
	 */
	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * Product dl expire str
	 */
	@SneakyThrows
	public void productDlExpireStr() {
		long milli = Instant.now().toEpochMilli();
		String content = "DeadLetter product message at " + milli;

		log.info("==>  " + content + " to queue=[{}]", DeadLetterConfig.DEAD_LETTER_QUEUE);

		MessagePostProcessor messagePostProcessor = message -> {
			MessageProperties messageProperties = message.getMessageProperties();
			messageProperties.setContentEncoding("UTF-8");
			messageProperties.setExpiration("10000");   // 10s expire
			return message;
		};

		// 注意 API 变化
		amqpTemplate.convertAndSend(DeadLetterConfig.DL_EXCHANGE, DeadLetterConfig.DEAD_LETTER_ROUTING_KEY, content, messagePostProcessor);
	}

	// TODO 拒绝

	// TODO 超载


}