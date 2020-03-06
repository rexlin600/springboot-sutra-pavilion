package xyz.rexlin600.runner.postconstruct.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 数字处理器
 *
 * @author: hekunlin
 * @date: 2020/3/6
 */
@Slf4j
@Service
public class IntegerHandler implements HandlerSvc {

    @Override
    public Integer handleCode() {
        return 2;
    }

    @Override
    public void handle(Object object) {
        Integer integer = (Integer) object;
        log.info("IntegerHandler handle object=【{}】", integer);
    }

}