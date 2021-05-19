package xyz.rexlin600.proxy.dynamic.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志SVC实现
 *
 * @author rexlin600
 */
public class LogServiceImpl implements LogService {

	private final static Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

	@Override
	public void info(String str) {
		log.info("==>  info log : {}", str);
	}

}
