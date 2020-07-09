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
 * SimpleAsyncCallback 异步任务回调
 *
 * @author: rexlin600
 * @since: 2020-01-12
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class SimpleAsyncCallback {


    private static Random random = new Random();

    @Async
    public Future<Long> taskOne() throws InterruptedException {
        long start = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
        log.info("==>  SimpleAsyncCallback taskOne execute start [{}]", start);

        Thread.sleep(random.nextInt(10000));

        long end = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
        log.info("==>  SimpleAsyncCallback taskOne execute end [{}]", end);

        return new AsyncResult<>(end - start);
    }

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