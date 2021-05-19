package xyz.rexlin600.proxy.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Purchase proxy.
 *
 * @author rexlin600
 */
public class PurchaseProxy implements PurchaseService {

	private final static Logger log = LoggerFactory.getLogger(PurchaseProxy.class);

	private final PurchaseService purchaseService;

	/**
	 * Instantiates a new Purchase proxy.
	 *
	 * @param purchaseService the purchase service
	 */
	public PurchaseProxy(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@Override
	public void buyBreakfast() {
		log.info("==>  Before invoke ...");

		purchaseService.buyBreakfast();

		log.info("==>  After invoke ...");
	}

}
