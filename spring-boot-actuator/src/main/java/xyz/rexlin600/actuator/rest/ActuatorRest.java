package xyz.rexlin600.actuator.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Actuator rest
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/actuator")
public class ActuatorRest {

	/**
	 * Index string
	 *
	 * @return the string
	 */
	@GetMapping("/index")
	public String index() {
		return "Hello World! Actuator";
	}

}