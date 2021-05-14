package xyz.rexlin600.elk.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import cn.hutool.core.util.StrUtil;

/**
 * 日志过滤器
 *
 * @author rexlin600
 */
public class LogBackFilter extends Filter<ILoggingEvent> {

	/**
	 * Decide filter reply.
	 *
	 * @param event the event
	 * @return the filter reply
	 */
	@SuppressWarnings("AlibabaRemoveCommentedCode")
	@Override
	public FilterReply decide(ILoggingEvent event) {
		// 采集的数据不能为空
		boolean notBlank = StrUtil.isNotBlank(event.getMessage());

		// 只允许WARN、ERROR的级别
//		boolean allowLevel = Objects.equals(event.getLevel().levelStr, Level.ERROR.toString()) ||
//				Objects.equals(event.getLevel().levelStr, Level.WARN.toString());

		if (notBlank) {
			return FilterReply.ACCEPT;
		}
		return FilterReply.DENY;
	}

}
