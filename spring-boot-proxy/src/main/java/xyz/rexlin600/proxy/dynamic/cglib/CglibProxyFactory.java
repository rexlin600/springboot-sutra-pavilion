package xyz.rexlin600.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 日志代理工厂
 *
 * @author rexlin600
 */
public class CglibProxyFactory {

	/**
	 * 获取代理
	 *
	 * @param clazz the clazz
	 * @return the proxy
	 */
	public static Object getProxy(Class<?> clazz) {
		// CGLIB通过 Enhancer 获取代理
		Enhancer enhancer = new Enhancer();

		enhancer.setClassLoader(clazz.getClassLoader());
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(new LogInterceptor());

		// 创建并返回代理对象
		Object o = enhancer.create();
		return o;
	}

}
