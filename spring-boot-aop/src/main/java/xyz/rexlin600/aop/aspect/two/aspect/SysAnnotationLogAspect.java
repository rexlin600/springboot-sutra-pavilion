package xyz.rexlin600.aop.aspect.two.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import xyz.rexlin600.aop.aspect.two.annotation.SysAopLog;
import xyz.rexlin600.aop.aspect.two.listener.SysLogEvent;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.util.SysLogUtils;


/**
 * 系统日志切面、注解版
 * <p>
 * 使用 @Aspect 定义一个切面
 *
 * @author: rexlin600
 * @date: 2020-02-16
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Aspect
@Component
public class SysAnnotationLogAspect {

    @Autowired
    private ApplicationEventPublisher publisher;

    @SneakyThrows
    @Around("@annotation(sysAopLog)")
    public Object around(ProceedingJoinPoint point, SysAopLog sysAopLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();

        log.info("==>  注解版本：[类名]:{},[方法]:{}", strClassName, strMethodName);

        SysLog logVo = SysLogUtils.getSysLog();
        logVo.setTitle(sysAopLog.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        logVo.setTime(endTime - startTime);

        publisher.publishEvent(new SysLogEvent(logVo));

        return obj;
    }

}