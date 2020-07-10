package xyz.rexlin600.rabbitmq.pattern.work.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.work.config.WorkConfig;

import java.time.Instant;

/**
 * Work customer
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class WorkCustomer {

	/**
	 * Handler work one *
	 *
	 * @param content content
	 * @param channel channel
	 * @param message message
	 */
	@RabbitListener(queuesToDeclare = {
			@Queue(WorkConfig.WORK_QUEUE)
	})
	@SneakyThrows
	@RabbitHandler
	public void handlerWorkOne(String content, Channel channel, Message message) {
		long milli = Instant.now().toEpochMilli();
		log.info("==>  Work one consume content=[{}] at [{}]", content, milli);
		// true表示一次确认所有小于tag的消息
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	/**
	 * Handler work two *
	 *
	 * @param content content
	 * @param channel channel
	 * @param message message
	 */
	@RabbitListener(queuesToDeclare = {
			@Queue(WorkConfig.WORK_QUEUE)
	})
	@SneakyThrows
	@RabbitHandler
	public void handlerWorkTwo(String content, Channel channel, Message message) {
		long milli = Instant.now().toEpochMilli();
		log.info("==>  Work two consume content=[{}] at [{}]", content, milli);
		// true表示一次确认所有小于tag的消息
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

}