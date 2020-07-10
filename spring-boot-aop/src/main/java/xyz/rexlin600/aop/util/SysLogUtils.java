package xyz.rexlin600.aop.util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.rexlin600.aop.entity.SysLog;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Sys log utils
 *
 * @author hekunlin
 */
@UtilityClass
public class SysLogUtils {

	/**
	 * STATUS_NORMAL
	 */
	private final static String STATUS_NORMAL = "0";

	/**
	 * Gets sys log *
	 *
	 * @return the sys log
	 */
	public SysLog getSysLog() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLog sysLog = new SysLog();
		sysLog.setType(STATUS_NORMAL);
		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		return sysLog;
	}

}
