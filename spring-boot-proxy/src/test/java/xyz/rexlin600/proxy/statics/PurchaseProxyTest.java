package xyz.rexlin600.proxy.statics;

import junit.framework.TestCase;

public class PurchaseProxyTest extends TestCase {

	public void testBuyBreakfast() {
		PurchaseProxy proxy = new PurchaseProxy(new PurchaseServiceImpl());
		proxy.buyBreakfast();
	}
}