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
 * @menu RabbitMQ相关API
 * @author: hekunlin
 * @since: 2020/1/7
 */
@SuppressWarnings("Duplicates")
@Slf4j
@RestController
@RequestMapping("/amqp")
public class AmqpRest {

    private SimpleProvider simpleProvider;
    private WorkProvider workProvider;
    private DirectProvider directProvider;
    private FanoutProvider fanoutProvider;
    private TopicProvider topicProvider;
    private DeadLetterProvider deadLetterProvider;


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
     * 1. 调用指定生产者的指定方法
     *
     * @param type   {@link InvokeTypeEnum}
     * @param method
     * @return
     */
    @SneakyThrows
    @PostMapping("/{type}/invoke/{method}")
    public Response invoke(@PathVariable(value = "type") String type,
                           @PathVariable(value = "method") String method) {
        // type check
        Optional<InvokeTypeEnum> any = Arrays.stream(InvokeTypeEnum.values()).filter(m -> {
            return m.getType().equals(type);
        }).findAny();
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
        Optional<Method> optional = Arrays.stream(methods).filter(m -> {
            return m.getName().equals(method);
        }).findAny();
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
     * code convert AmqpInvoke Class
     *
     * @param code
     * @return
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