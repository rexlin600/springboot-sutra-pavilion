package xyz.rexlin600.aop.aspect.two.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.rexlin600.aop.entity.SysLog;

/**
 * 系统日志事件
 *
 * @author rexlin600
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {
    private final SysLog sysLog;
}
