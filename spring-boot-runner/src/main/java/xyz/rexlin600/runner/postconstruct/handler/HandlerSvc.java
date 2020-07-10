package xyz.rexlin600.runner.postconstruct.handler;

import org.springframework.stereotype.Component;

/**
 * Handler svc
 *
 * @author hekunlin
 */
@Component
public interface HandlerSvc {

	/**
	 * Handle code integer
	 *
	 * @return the integer
	 */
	Integer handleCode();

	/**
	 * Handle *
	 *
	 * @param object object
	 */
	void handle(Object object);

}