package xyz.rexlin600.aop.aspect.two.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import xyz.rexlin600.aop.aspect.two.feign.RemoteSysLogService;
import xyz.rexlin600.aop.entity.SysLog;

/**
 * 异步监听日志事件
 *
 * @author rexlin600
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    private final RemoteSysLogService remoteSysLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = event.getSysLog();
        remoteSysLogService.saveLog(sysLog, "Y");
    }
}
