package xyz.rexlin600.runner.postconstruct.handler;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网关服务
 *
 * @author: hekunlin
 * @date: 2020/3/6
 */
@Component
public interface HandlerSvc {

    /**
     * 处理编码
     */
    Integer handleCode();

    /**
     * Handle处理
     *
     * @param object
     */
    void handle(Object object);

}