package xyz.rexlin600.proxy.dynamic.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Sms service.
 *
 * @author rexlin600
 */
public class SmsServiceImpl implements SmsService {

	private final static Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Override
	public void send(String message) {
		log.info("==>  JDK Proxy receive message is : {}", message);
	}

}
