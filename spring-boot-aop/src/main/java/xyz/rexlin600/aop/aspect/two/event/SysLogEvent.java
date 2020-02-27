package xyz.rexlin600.aop.aspect.two.event;

import org.springframework.context.ApplicationEvent;
import xyz.rexlin600.aop.entity.SysLog;

/**
 * 系统日志事件
 *
 * @author rexlin600
 */
public class SysLogEvent extends ApplicationEvent {

    private SysLog sysLog;

    public SysLogEvent(Object source) {
        super(source);
    }

    public SysLogEvent(Object source, SysLog sysLog) {
        super(source);
        this.sysLog = sysLog;
    }
}
