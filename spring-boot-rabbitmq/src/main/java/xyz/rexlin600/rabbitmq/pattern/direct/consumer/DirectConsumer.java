package xyz.rexlin600.rabbitmq.pattern.direct.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.direct.config.DirectConfig;

import java.time.Instant;

/**
 * Direct consumer
 *
 * @author hekunlin
 */
@Slf4j
@Component
@RabbitListener(queues = DirectConfig.DIRECT_QUEUE)
public class DirectConsumer {

	/**
	 * Handler direct str *
	 *
	 * @param content content
	 * @param channel channel
	 * @param message message
	 */
	@SneakyThrows
	@RabbitHandler
	public void handlerDirectStr(String content, Channel channel, Message message) {
		long milli = Instant.now().toEpochMilli();
		log.info("==>  Direct consume content=[{}] at [{}]", content, milli);
		// true表示一次确认所有小于tag的消息
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

}