package xyz.rexlin600.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.Random;

/**
 * Simple task
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class SimpleTask {

	/**
	 * random
	 */
	private static Random random = new Random();

	/**
	 * Task one *
	 *
	 * @throws InterruptedException interrupted exception
	 */
	public void taskOne() throws InterruptedException {
		log.info("==>  SimpleTask taskOne execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
		Thread.sleep(random.nextInt(3000));
		log.info("==>  SimpleTask taskOne execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
	}

	/**
	 * Task two *
	 *
	 * @throws InterruptedException interrupted exception
	 */
	public void taskTwo() throws InterruptedException {
		log.info("==>  SimpleTask taskTwo execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
		Thread.sleep(random.nextInt(3000));
		log.info("==>  SimpleTask taskTwo execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
	}

	/**
	 * Task three *
	 *
	 * @throws InterruptedException interrupted exception
	 */
	public void taskThree() throws InterruptedException {
		log.info("==>  SimpleTask taskThree execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
		Thread.sleep(random.nextInt(3000));
		log.info("==>  SimpleTask taskThree execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
	}

}