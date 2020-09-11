package xyz.rexlin600.rabbitmq.rest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.rabbitmq.common.apiparam.Response;
import xyz.rexlin600.rabbitmq.common.apiparam.ResponseGenerator;
import xyz.rexlin600.rabbitmq.common.enums.InvokeTypeEnum;
import xyz.rexlin600.rabbitmq.entity.AmqpInvoke;
import xyz.rexlin600.rabbitmq.pattern.deadletter.provider.DeadLetterProvider;
import xyz.rexlin600.rabbitmq.pattern.direct.provider.DirectProvider;
import xyz.rexlin600.rabbitmq.pattern.fanout.provider.FanoutProvider;
import xyz.rexlin600.rabbitmq.pattern.simple.provider.SimpleProvider;
import xyz.rexlin600.rabbitmq.pattern.topic.provider.TopicProvider;
import xyz.rexlin600.rabbitmq.pattern.work.provider.WorkProvider;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/**
 * RabbitMQ 接口
 *
 * @author hekunlin
 */
@SuppressWarnings("Duplicates")
@Slf4j
@RestController
@RequestMapping("/amqp")
public class AmqpRest {

	/**
	 * Simple provider
	 */
	private SimpleProvider simpleProvider;
	/**
	 * Work provider
	 */
	private WorkProvider workProvider;
	/**
	 * Direct provider
	 */
	private DirectProvider directProvider;
	/**
	 * Fanout provider
	 */
	private FanoutProvider fanoutProvider;
	/**
	 * Topic provider
	 */
	private TopicProvider topicProvider;
	/**
	 * Dead letter provider
	 */
	private DeadLetterProvider deadLetterProvider;


	/**
	 * Amqp rest
	 *
	 * @param simpleProvider     simple provider
	 * @param workProvider       work provider
	 * @param directProvider     direct provider
	 * @param fanoutProvider     fanout provider
	 * @param topicProvider      topic provider
	 * @param deadLetterProvider dead letter provider
	 */
	@Autowired
	public AmqpRest(SimpleProvider simpleProvider,
					WorkProvider workProvider,
					DirectProvider directProvider,
					FanoutProvider fanoutProvider,
					TopicProvider topicProvider,
					DeadLetterProvider deadLetterProvider) {
		this.simpleProvider = simpleProvider;
		this.workProvider = workProvider;
		this.directProvider = directProvider;
		this.fanoutProvider = fanoutProvider;
		this.topicProvider = topicProvider;
		this.deadLetterProvider = deadLetterProvider;
	}

	/**
	 * 发送消息
	 *
	 * @param type   type	0-default 1-direct 2-fanout 3-header 4-topic 5-simple 6-work 7-deadLetter 8-custom
	 * @param method method	directProductStr、fanoutProductStr、topicProductStr1、topicProductStr2、topicProductStr3、
	 *               simpleProductStr、workProductStr、productDlExpireStr
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/{type}/invoke/{method}")
	public Response invoke(@PathVariable(value = "type") String type,
						   @PathVariable(value = "method") String method) {
		// type check
		Optional<InvokeTypeEnum> any = Arrays.stream(InvokeTypeEnum.values()).filter(m -> m.getType().equals(type)).findAny();
		if (!any.isPresent()) {
			log.error("==>  Don't match this type=[{}]", type);
			return ResponseGenerator.fail("not have type");
		}
		Integer code = any.get().getCode();

		// get AmqpInvoke class
		AmqpInvoke amqpInvoke = codeConvertAmqpInvoke(code);
		if (amqpInvoke == null) {
			log.error("==>  Don't match this type=[{}]", type);
			return ResponseGenerator.fail("not have type");
		}

		// methods check
		Method[] methods = amqpInvoke.getMethods();
		Optional<Method> optional = Arrays.stream(methods).filter(m -> m.getName().equals(method)).findAny();
		if (!optional.isPresent()) {
			log.error("==>  Don't match this type=[{}]", type);
			return ResponseGenerator.fail("not have method");
		}

		// get instance obj
		Object obj = amqpInvoke.getObject();

		// invoke
		Method m = optional.get();
		m.invoke(obj, Collections.emptyList());

		return ResponseGenerator.success();
	}


	// -----------------------------------------------------------------------------------------------
	// OTHER METHODS
	// -----------------------------------------------------------------------------------------------

	/**
	 * Code convert amqp invoke amqp invoke
	 *
	 * @param code code
	 * @return the amqp invoke
	 */
	public AmqpInvoke codeConvertAmqpInvoke(Integer code) {
		AmqpInvoke amqpInvoke = null;
		switch (code) {
			// default
			case 0:
				break;
			// direct
			case 1:
				amqpInvoke = new AmqpInvoke(directProvider.getClass().getDeclaredMethods(), directProvider);
				break;
			// fanout
			case 2:
				amqpInvoke = new AmqpInvoke(fanoutProvider.getClass().getDeclaredMethods(), fanoutProvider);
				break;
			// header
			case 3:
				break;
			// topic
			case 4:
				amqpInvoke = new AmqpInvoke(topicProvider.getClass().getDeclaredMethods(), topicProvider);
				break;
			// simple
			case 5:
				amqpInvoke = new AmqpInvoke(simpleProvider.getClass().getDeclaredMethods(), simpleProvider);
				break;
			// work
			case 6:
				amqpInvoke = new AmqpInvoke(workProvider.getClass().getDeclaredMethods(), workProvider);
				break;
			// dl
			case 7:
				amqpInvoke = new AmqpInvoke(deadLetterProvider.getClass().getDeclaredMethods(), deadLetterProvider);
				break;
			// custom
			case 8:
				break;
			default:
				break;
		}
		return amqpInvoke;
	}


}