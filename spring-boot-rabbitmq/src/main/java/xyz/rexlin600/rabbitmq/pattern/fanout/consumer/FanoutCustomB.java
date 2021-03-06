package xyz.rexlin600.rabbitmq.pattern.fanout.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.rexlin600.rabbitmq.pattern.fanout.config.FanoutConfig;

import java.time.Instant;

/**
 * Fanout custom b
 *
 * @author hekunlin
 */
@Slf4j
@Component
@RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_B)
public class FanoutCustomB {

	/**
	 * Handler fan out str b *
	 *
	 * @param content content
	 * @param channel channel
	 * @param message message
	 */
	@SneakyThrows
	@RabbitHandler
	public void handlerFanOutStrB(String content, Channel channel, Message message) {
		long milli = Instant.now().toEpochMilli();
		log.info("==>  Fanout B consume content=[{}] at [{}]", content, milli);
		// true表示一次确认所有小于tag的消息
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

}