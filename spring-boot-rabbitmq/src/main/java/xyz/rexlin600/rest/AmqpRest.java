package xyz.rexlin600.rest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.common.apiparam.Response;
import xyz.rexlin600.common.apiparam.ResponseGenerator;
import xyz.rexlin600.common.enums.InvokeTypeEnum;
import xyz.rexlin600.entity.AmqpInvoke;
import xyz.rexlin600.rabbit.direct.provider.DirectProvider;
import xyz.rexlin600.rabbit.fanout.provider.FanoutProvider;
import xyz.rexlin600.rabbit.simple.provider.SimpleProvider;
import xyz.rexlin600.rabbit.topic.provider.TopicProvider;
import xyz.rexlin600.rabbit.work.provider.WorkProvider;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/**
 * @menu AMQP_测试API
 * @author: hekunlin
 * @date: 2020/1/7
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

    @Autowired
    public AmqpRest(SimpleProvider simpleProvider,
                    WorkProvider workProvider,
                    DirectProvider directProvider,
                    FanoutProvider fanoutProvider,
                    TopicProvider topicProvider) {
        this.simpleProvider = simpleProvider;
        this.workProvider = workProvider;
        this.directProvider = directProvider;
        this.fanoutProvider = fanoutProvider;
        this.topicProvider = topicProvider;
    }

    /**
     * 1. 【调用指定生产者的指定方法】
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
        m.invoke(obj, null);

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
            case 0: // default
                break;
            case 1: // direct
                amqpInvoke = new AmqpInvoke(directProvider.getClass().getDeclaredMethods(), directProvider);
                break;
            case 2: // fanout
                amqpInvoke = new AmqpInvoke(fanoutProvider.getClass().getDeclaredMethods(), fanoutProvider);
                break;
            case 3: // header
                break;
            case 4: // topic
                amqpInvoke = new AmqpInvoke(topicProvider.getClass().getDeclaredMethods(), topicProvider);
                break;
            case 5: // simple
                amqpInvoke = new AmqpInvoke(simpleProvider.getClass().getDeclaredMethods(), simpleProvider);
                break;
            case 6: // work
                amqpInvoke = new AmqpInvoke(workProvider.getClass().getDeclaredMethods(), workProvider);
                break;
            case 7: // custom
                break;
            default:
                break;
        }
        return amqpInvoke;
    }


}