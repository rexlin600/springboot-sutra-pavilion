package xyz.rexlin600.actuator.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Actuator监控
 *
 * @author: hekunlin
 * @since: 2020/1/8
 */
@RestController
@RequestMapping("/actuator")
public class ActuatorRest {

    /**
     * 返回index
     *
     * @return {@link String}
     */
    @GetMapping("/index")
    public String index() {
        return "Hello World! Actuator";
    }

}