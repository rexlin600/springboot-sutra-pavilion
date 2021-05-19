package xyz.rexlin600.proxy.dynamic.jdk;

import junit.framework.TestCase;

public class JdkProxyFactoryTest extends TestCase {

	public void testGetProxy() {
		SmsService proxy = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
		proxy.send("Kobe");
	}

}