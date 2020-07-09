package xyz.rexlin600.runner.postconstruct.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 字符串处理器
 *
 * @author: hekunlin
 * @since: 2020/3/6
 */
@Slf4j
@Service
public class StringHandler implements HandlerSvc {

    @Override
    public Integer handleCode() {
        return 1;
    }

    @Override
    public void handle(Object object) {
        String str = (String) object;
        log.info("StringHandler handle object=[{}]", str);
    }

}