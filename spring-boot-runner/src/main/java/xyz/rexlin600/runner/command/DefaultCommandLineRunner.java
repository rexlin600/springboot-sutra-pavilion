package xyz.rexlin600.runner.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: hekunlin
 */
@Component
@Slf4j
public class DefaultCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("==>  CommandLineRunner start " + this.getClass().getName() + " ...");
    }

}