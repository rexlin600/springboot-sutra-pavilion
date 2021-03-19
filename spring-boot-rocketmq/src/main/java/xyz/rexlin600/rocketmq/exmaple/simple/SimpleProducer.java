package xyz.rexlin600.rocketmq.exmaple.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 同步消息生产者
 *
 * @author rexlin600
 */
@Slf4j
public class SimpleProducer {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		// 发送消息
		DefaultMQProducer defaultMqProducer = new DefaultMQProducer("DEFAULT_GROUP");
		defaultMqProducer.setNamesrvAddr("192.168.4.16:9876");
		try {
			defaultMqProducer.start();
			Message message = new Message("DEFAULT_TOPIC", "DEFAULT_TAG", "这是一首简单的小情歌".getBytes(StandardCharsets.UTF_8));
			SendResult sendResult = defaultMqProducer.send(message);
			log.info("==>  发送消息结果：{}", sendResult.toString());
		} catch (MQClientException | InterruptedException | RemotingException | MQBrokerException e) {
			e.printStackTrace();
		} finally {
			defaultMqProducer.shutdown();
		}

		// 接收消息
		DefaultMQPushConsumer defaultMqPushConsumer = new DefaultMQPushConsumer("DEFAULT_GROUP");
		defaultMqPushConsumer.setNamesrvAddr("192.168.4.16:9876");
		try {
			defaultMqPushConsumer.subscribe("DEFAULT_TOPIC", "*");
			defaultMqPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
				@Override
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
					log.info("==>  {} Receive New Messages: {}", Thread.currentThread().getName(), list);
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});
			defaultMqPushConsumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}

	}
}
