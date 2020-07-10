package xyz.rexlin600.common.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Ip utils
 *
 * @author hekunlin
 */
public class IpUtils {

	/**
	 * Unknown
	 */
	private final String UNKNOWN = "unknown";

	/**
	 * Gets ip *
	 *
	 * @return the ip
	 */
	public String getIp() {
		return getIp(WebUtils.getRequest());
	}

	/**
	 * Gets ip *
	 *
	 * @param request request
	 * @return the ip
	 */
	public String getIp(HttpServletRequest request) {
		Assert.notNull(request, "HttpServletRequest is null");
		String ip = request.getHeader("X-Requested-For");
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return StrUtil.isBlank(ip) ? null : ip.split(",")[0];
	}

}