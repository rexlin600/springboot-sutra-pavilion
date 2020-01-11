package xyz.rexlin600.elasticsearch.runner.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: hekunlin
 */
@Component
@Order(1)
@Slf4j
public class FirstCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("==>  CommandLineRunner start " + this.getClass().getName() + " ...");
    }
}