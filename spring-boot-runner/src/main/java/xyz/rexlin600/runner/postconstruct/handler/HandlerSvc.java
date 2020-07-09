package xyz.rexlin600.runner.postconstruct.handler;

import org.springframework.stereotype.Component;

/**
 * 网关服务
 *
 * @author: hekunlin
 * @since: 2020/3/6
 */
@Component
public interface HandlerSvc {

    /**
     * 处理编码
     *
     * @return
     */
    Integer handleCode();

    /**
     * Handle处理
     *
     * @param object
     */
    void handle(Object object);

}