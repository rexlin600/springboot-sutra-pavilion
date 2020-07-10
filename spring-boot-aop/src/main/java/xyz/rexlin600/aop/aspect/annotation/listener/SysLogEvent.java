package xyz.rexlin600.aop.aspect.annotation.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.rexlin600.aop.entity.SysLog;

/**
 * Sys log event
 *
 * @author hekunlin
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {

	/**
	 * Sys log
	 */
	private final SysLog sysLog;

}
