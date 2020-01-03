package xyz.rexlin600.config.runner.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: hekunlin
 * @date: 2020/1/2
 */
@Component
@Slf4j
@Order(2)
public class SecondApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("==>  ApplicationRunner start " + this.getClass().getName() + " , args=[" + args + "] ...");
    }

}