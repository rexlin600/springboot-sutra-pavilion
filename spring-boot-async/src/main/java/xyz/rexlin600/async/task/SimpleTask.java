package xyz.rexlin600.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.Random;

/**
 * SimpleTask 顺序执行任务
 *
 * @author: rexlin600
 * @since: 2020-01-12
 */
@Slf4j
@Component
public class SimpleTask {

    private static Random random = new Random();

    public void taskOne() throws InterruptedException {
        log.info("==>  SimpleTask taskOne execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        Thread.sleep(random.nextInt(3000));
        log.info("==>  SimpleTask taskOne execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
    }

    public void taskTwo() throws InterruptedException {
        log.info("==>  SimpleTask taskTwo execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        Thread.sleep(random.nextInt(3000));
        log.info("==>  SimpleTask taskTwo execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
    }

    public void taskThree() throws InterruptedException {
        log.info("==>  SimpleTask taskThree execute start [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
        Thread.sleep(random.nextInt(3000));
        log.info("==>  SimpleTask taskThree execute end [{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
    }

}