package xyz.rexlin600.elk.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取服务器IP、端口
 *
 * @author rexlin600
 */
public class LoggingIpConverter extends ClassicConverter {

	private static String machineIp;

	static {
		try {
			machineIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			machineIp = null;
		}
	}

	@Override
	public String convert(ILoggingEvent iLoggingEvent) {
		return machineIp;
	}

}
