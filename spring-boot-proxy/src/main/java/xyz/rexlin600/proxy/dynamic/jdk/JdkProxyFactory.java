package xyz.rexlin600.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * The type Jdk proxy factory.
 *
 * @author rexlin600
 */
public class JdkProxyFactory {

	/**
	 * Gets proxy.
	 *
	 * @param target the target
	 * @return the proxy
	 */
	public static Object getProxy(Object target) {
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new SmsInvokeHandler(target));
	}

}
