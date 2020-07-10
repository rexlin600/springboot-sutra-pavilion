package xyz.rexlin600.runner.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Default application runner
 *
 * @author hekunlin
 */
@Component
@Slf4j
public class DefaultApplicationRunner implements ApplicationRunner {

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