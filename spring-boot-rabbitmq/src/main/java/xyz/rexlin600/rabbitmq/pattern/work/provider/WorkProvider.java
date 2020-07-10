package xyz.rexlin600.rabbitmq.pattern.work.provider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.work.config.WorkConfig;

import java.time.Instant;

/**
 * Work provider
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class WorkProvider {

	/**
	 * Amqp template
	 */
	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * Work product str
	 */
	@SneakyThrows
	public void workProductStr() {
		long milli = Instant.now().toEpochMilli();
		String content = "Work product message at " + milli;
		log.info("==>  " + content + " to queue=[{}] and at [{}]", WorkConfig.WORK_QUEUE, milli);
		amqpTemplate.convertAndSend(WorkConfig.WORK_QUEUE, content);
	}

}