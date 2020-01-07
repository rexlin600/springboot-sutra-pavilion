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

    private DirectProvider directProvider;

    @Autowired
    public AmqpRest(DirectProvider directProvider) {
        this.directProvider = directProvider;
    }

    /**
     * 1. 【调用指定生产者的指定方法】
     *
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
                amqpInvoke = new AmqpInvoke(directProvider.getClass().getDeclaredMethods(), directProvider);
                break;
            case 1: // direct
                amqpInvoke = new AmqpInvoke(directProvider.getClass().getDeclaredMethods(), directProvider);
                break;
            case 2: // fanout
                amqpInvoke = new AmqpInvoke(directProvider.getClass().getDeclaredMethods(), directProvider);
                break;
            case 3: // header
                amqpInvoke = new AmqpInvoke(directProvider.getClass().getDeclaredMethods(), directProvider);
                break;
            case 4: // topic
                amqpInvoke = new AmqpInvoke(directProvider.getClass().getDeclaredMethods(), directProvider);
                break;
            case 5: // custom
                amqpInvoke = new AmqpInvoke(directProvider.getClass().getDeclaredMethods(), directProvider);
                break;
            default:
                break;
        }
        return amqpInvoke;
    }


}