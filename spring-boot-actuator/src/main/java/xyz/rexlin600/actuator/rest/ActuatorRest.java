package xyz.rexlin600.docker.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Actuator API
 *
 * @author: hekunlin
 * @date: 2020/1/8
 */
@RestController
@RequestMapping("/actuator")
public class ActuatorRest {


    @GetMapping("/index")
    public String index() {
        return "Hello World! Actuator";
    }

}