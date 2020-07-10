package xyz.rexlin600.runner.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Default command line runner
 *
 * @author hekunlin
 */
@Component
@Slf4j
public class DefaultCommandLineRunner implements CommandLineRunner {

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