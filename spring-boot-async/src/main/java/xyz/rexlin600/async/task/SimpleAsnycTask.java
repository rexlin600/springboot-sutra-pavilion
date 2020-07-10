package xyz.rexlin600.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.Random;

/**
 * Simple asnyc task
 *
 * @author hekunlin
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class SimpleAsnycTask {

	/**
	 * random
	 */
	private static Random random = new Random();

	/**
	 * Task one *
	 *
	 * @throws InterruptedException interrupted exception
	 */
	@Async
	public void taskOne() throws InterruptedException {
		log.info("==>  SimpleAsnycTask taskOne execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
		Thread.sleep(random.nextInt(3000));
		log.info("==>  SimpleAsnycTask taskOne execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
	}

	/**
	 * Task two *
	 *
	 * @throws InterruptedException interrupted exception
	 */
	@Async
	public void taskTwo() throws InterruptedException {
		log.info("==>  SimpleAsnycTask taskTwo execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
		Thread.sleep(random.nextInt(3000));
		log.info("==>  SimpleAsnycTask taskTwo execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
	}

	/**
	 * Task three *
	 *
	 * @throws InterruptedException interrupted exception
	 */
	@Async
	public void taskThree() throws InterruptedException {
		log.info("==>  SimpleAsnycTask taskThree execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
		Thread.sleep(random.nextInt(3000));
		log.info("==>  SimpleAsnycTask taskThree execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
	}

}