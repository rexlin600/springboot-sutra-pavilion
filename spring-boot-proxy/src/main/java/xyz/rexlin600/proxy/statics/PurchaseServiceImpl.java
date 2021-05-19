package xyz.rexlin600.proxy.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Purchase service.
 *
 * @author rexlin600
 */
public class PurchaseServiceImpl implements PurchaseService {

	private static final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	@Override
	public void buyBreakfast() {
		log.info("==>  Buy BreakFast ...");
	}

}
