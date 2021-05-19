package xyz.rexlin600.proxy.dynamic.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * The type Sms invoke handler.
 *
 * @author rexlin600
 */
public class SmsInvokeHandler implements InvocationHandler {

	private final static Logger log = LoggerFactory.getLogger(SmsInvokeHandler.class);

	private final Object target;

	/**
	 * Instantiates a new Sms invoke handler.
	 *
	 * @param target the target
	 */
	public SmsInvokeHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("==>  Before invoke ...");

		Object result = method.invoke(target, args);

		log.info("==>  After invoke ...");
		return result;
	}

}
