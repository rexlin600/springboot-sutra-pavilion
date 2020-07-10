package xyz.rexlin600.runner.postconstruct.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Integer handler
 *
 * @author hekunlin
 */
@Slf4j
@Service
public class IntegerHandler implements HandlerSvc {

	/**
	 * Handle code integer
	 *
	 * @return the integer
	 */
	@Override
	public Integer handleCode() {
		return 2;
	}

	/**
	 * Handle *
	 *
	 * @param object object
	 */
	@Override
	public void handle(Object object) {
		Integer integer = (Integer) object;
		log.info("IntegerHandler handle object=[{}]", integer);
	}

}