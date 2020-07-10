package xyz.rexlin600.rabbitmq.pattern.simple.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.simple.config.SimpleConfig;

import java.time.Instant;

/**
 * Simple provider
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class SimpleProvider {

	/**
	 * Amqp template
	 */
	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * Simple product str
	 */
	@SneakyThrows
	public void simpleProductStr() {
		long milli = Instant.now().toEpochMilli();
		String content = "Simple product message at " + milli;
		log.info("==>  " + content + " to queue=[{}] and at [{}]", SimpleConfig.SIMPLE_QUEUE, milli);
		amqpTemplate.convertAndSend(SimpleConfig.SIMPLE_QUEUE, content);
	}

}