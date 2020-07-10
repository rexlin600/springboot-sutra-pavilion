package xyz.rexlin600.runner.postconstruct.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * String handler
 *
 * @author hekunlin
 */
@Slf4j
@Service
public class StringHandler implements HandlerSvc {

	/**
	 * Handle code integer
	 *
	 * @return the integer
	 */
	@Override
	public Integer handleCode() {
		return 1;
	}

	/**
	 * Handle *
	 *
	 * @param object object
	 */
	@Override
	public void handle(Object object) {
		String str = (String) object;
		log.info("StringHandler handle object=[{}]", str);
	}

}