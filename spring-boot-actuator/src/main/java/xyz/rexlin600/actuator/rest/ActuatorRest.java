package xyz.rexlin600.actuator.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @menu Actuator监控
 * @author: hekunlin
 * @since: 2020/1/8
 */
@RestController
@RequestMapping("/actuator")
public class ActuatorRest {

    /**
     * 1. 返回index
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "Hello World! Actuator";
    }

}