package xyz.rexlin600.proxy.dynamic.cglib;

import junit.framework.TestCase;

public class CglibProxyFactoryTest extends TestCase {

	public void testGetProxy() {
		// 获取代理
		LogService proxy = (LogService) CglibProxyFactory.getProxy(LogServiceImpl.class);

		proxy.info("Kobe");
	}

}