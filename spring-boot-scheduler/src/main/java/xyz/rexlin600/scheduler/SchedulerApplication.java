package xyz.rexlin600.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Scheduler application
 *
 * @author hekunlin
 */
@EnableScheduling
@SpringBootApplication
public class SchedulerApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

}