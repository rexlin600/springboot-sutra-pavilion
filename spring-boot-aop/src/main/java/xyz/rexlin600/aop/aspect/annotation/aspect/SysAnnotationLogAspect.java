package xyz.rexlin600.aop.aspect.annotation.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import xyz.rexlin600.aop.aspect.annotation.annotation.SysAopLog;
import xyz.rexlin600.aop.aspect.annotation.listener.SysLogEvent;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.util.SysLogUtils;


/**
 * Sys annotation log aspect
 *
 * @author hekunlin
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Aspect
@Component
public class SysAnnotationLogAspect {

	/**
	 * Publisher
	 */
	@Autowired
	private ApplicationEventPublisher publisher;

	/**
	 * Around object
	 *
	 * @param point     point
	 * @param sysAopLog sys aop log
	 * @return the object
	 */
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