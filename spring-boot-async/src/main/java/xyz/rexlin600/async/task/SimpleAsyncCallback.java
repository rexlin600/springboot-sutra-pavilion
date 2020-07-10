package xyz.rexlin600.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * Simple async callback
 *
 * @author hekunlin
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class SimpleAsyncCallback {


	/**
	 * random
	 */
	private static Random random = new Random();

	/**
	 * Task one future
	 *
	 * @return the future
	 * @throws InterruptedException interrupted exception
	 */
	@Async
	public Future<Long> taskOne() throws InterruptedException {
		long start = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
		log.info("==>  SimpleAsyncCallback taskOne execute start [{}]", start);

		Thread.sleep(random.nextInt(10000));

		long end = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
		log.info("==>  SimpleAsyncCallback taskOne execute end [{}]", end);

		return new AsyncResult<>(end - start);
	}

	/**
	 * Task two future
	 *
	 * @return the future
	 * @throws InterruptedException interrupted exception
	 */
	@Async
	public Future<Long> taskTwo() throws InterruptedException {
		long start = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
		log.info("==>  SimpleAsyncCallback taskTwo execute start [{}]", start);

		Thread.sleep(random.nextInt(10000));

		long end = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
		log.info("==>  SimpleAsyncCallback taskTwo execute end [{}]", end);

		return new AsyncResult<>(end - start);
	}


}