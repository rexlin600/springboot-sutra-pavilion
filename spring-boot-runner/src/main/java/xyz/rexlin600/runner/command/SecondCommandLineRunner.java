package xyz.rexlin600.runner.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Second command line runner
 *
 * @author hekunlin
 */
@Component
@Order(2)
@Slf4j
public class SecondCommandLineRunner implements CommandLineRunner {

	/**
	 * Run *
	 *
	 * @param args args
	 * @throws Exception exception
	 */
	@Override
	public void run(String... args) throws Exception {
		log.info("==>  CommandLineRunner start " + this.getClass().getName() + " ...");
	}

}