package xyz.rexlin600.config.runner.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: hekunlin
 */
@Component
@Order(2)
@Slf4j
public class SecondCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("==>  CommandLineRunner start " + this.getClass().getName() + " ...");
    }

}