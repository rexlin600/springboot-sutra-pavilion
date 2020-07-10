package xyz.rexlin600.rabbitmq.pattern.deadletter.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import xyz.rexlin600.rabbitmq.pattern.deadletter.config.DeadLetterConfig;

import java.time.Instant;

/**
 * Dead letter consumer
 *
 * @author hekunlin
 */
@Slf4j
@RabbitListener(queues = DeadLetterConfig.DEAD_LETTER_QUEUE)
public class DeadLetterConsumer {

	/**
	 * Handler dl str *
	 *
	 * @param content content
	 * @param channel channel
	 * @param message message
	 */
	@SneakyThrows
	@RabbitHandler
	public void handlerDlStr(String content, Channel channel, Message message) {
		long milli = Instant.now().toEpochMilli();
		log.info("==>  DeadLetter consume content=[{}] at [{}]", content, milli);
		// true表示一次确认所有小于tag的消息
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

}