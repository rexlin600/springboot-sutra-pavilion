package xyz.rexlin600.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.Random;

/**
 * SimpleAsyncTask 异步执行任务
 *
 * @author: rexlin600
 * @since: 2020-01-12
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class SimpleAsnycTask {

    private static Random random = new Random();

    @Async
    public void taskOne() throws InterruptedException {
        log.info("==>  SimpleAsnycTask taskOne execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        Thread.sleep(random.nextInt(3000));
        log.info("==>  SimpleAsnycTask taskOne execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
    }

    @Async
    public void taskTwo() throws InterruptedException {
        log.info("==>  SimpleAsnycTask taskTwo execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        Thread.sleep(random.nextInt(3000));
        log.info("==>  SimpleAsnycTask taskTwo execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
    }

    @Async
    public void taskThree() throws InterruptedException {
        log.info("==>  SimpleAsnycTask taskThree execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        Thread.sleep(random.nextInt(3000));
        log.info("==>  SimpleAsnycTask taskThree execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
    }

}