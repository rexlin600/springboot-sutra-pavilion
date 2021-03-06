package xyz.rexlin600.aop.aspect.annotation.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import xyz.rexlin600.aop.aspect.annotation.feign.RemoteSysLogService;
import xyz.rexlin600.aop.entity.SysLog;

import java.time.Instant;

/**
 * Sys log listener
 *
 * @author hekunlin
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Component
@AllArgsConstructor
public class SysLogListener {

	/**
	 * Remote sys log service
	 */
	private final RemoteSysLogService remoteSysLogService;

	/**
	 * On application event *
	 *
	 * @param event event
	 */
	@Async
	@Order
	@EventListener
	public void onApplicationEvent(SysLogEvent event) {
		SysLog source = event.getSysLog();
		log.info("==>  注解版本：处理监听事件参数:{}, 时间戳:{}", source.toString(), Instant.now().toEpochMilli());
		// 模拟调用远程服务实现增加操作日志
		remoteSysLogService.add(source);
	}
}
