package xyz.rexlin600.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 日志拦截器
 *
 * @author rexlin600
 */
public class LogInterceptor implements MethodInterceptor {

	private final static Logger log = LoggerFactory.getLogger(LogInterceptor.class);

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		log.info("==>  Before invoke");

		Object result = proxy.invokeSuper(obj, args);

		log.info("==>  After invoke");
		return result;
	}
}
