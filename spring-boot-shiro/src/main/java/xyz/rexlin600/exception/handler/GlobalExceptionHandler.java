package xyz.rexlin600.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.rexlin600.common.enums.ResponseEnum;
import xyz.rexlin600.common.resp.Response;

/**
 * 全局异常处理
 *
 * @author: rexlin600
 * @date: 2020-01-19
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Response defaultException(Exception ex) {
        log.error("==>  系统默认异常 [{}]", ex.getMessage());
        return Response.failWithCodeAndMsg(ResponseEnum.FAIL.getCode(), ex.getMessage());
    }


}