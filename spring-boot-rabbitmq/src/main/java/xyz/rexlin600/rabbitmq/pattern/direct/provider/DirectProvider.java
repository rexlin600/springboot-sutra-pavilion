package xyz.rexlin600.rabbitmq.pattern.direct.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.direct.config.DirectConfig;

import java.time.Instant;

/**
 * Direct provider
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class DirectProvider {

	/**
	 * Amqp template
	 */
	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * Direct product str
	 */
	@SneakyThrows
	public void directProductStr() {
		long milli = Instant.now().toEpochMilli();
		String content = "Direct product message at " + milli;
		log.info("==> " + content + " to queue=[{}] and at [{}]", DirectConfig.DIRECT_QUEUE, milli);
		amqpTemplate.convertAndSend(DirectConfig.DIRECT_QUEUE, content);
	}


}