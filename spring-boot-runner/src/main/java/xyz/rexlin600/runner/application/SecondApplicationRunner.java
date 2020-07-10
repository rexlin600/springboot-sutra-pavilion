package xyz.rexlin600.runner.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Second application runner
 *
 * @author hekunlin
 */
@Component
@Slf4j
@Order(2)
public class SecondApplicationRunner implements ApplicationRunner {

	/**
	 * Run *
	 *
	 * @param args args
	 * @throws Exception exception
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("==>  ApplicationRunner start " + this.getClass().getName() + " , args=[" + args + "] ...");
	}

}