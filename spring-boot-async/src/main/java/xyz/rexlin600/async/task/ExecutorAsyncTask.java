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
 * ExecutorAsyncTask 异步线程池任务
 *
 * @author: rexlin600
 * @date: 2020-01-12
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class ExecutorAsyncTask {

    private static Random random = new Random();

    @Async(value = "taskExecutor")
    public Future<Long> taskOne() throws InterruptedException {
        long start = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
        log.info("==>  ExecutorAsyncTask taskOne execute start [{}]", start);

        Thread.sleep(random.nextInt(10000));

        long end = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
        log.info("==>  ExecutorAsyncTask taskOne execute end [{}]", end);

        return new AsyncResult<>(end - start);
    }

    @Async(value = "taskExecutor")
    public Future<Long> taskTwo() throws InterruptedException {
        long start = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
        log.info("==>  ExecutorAsyncTask taskTwo execute start [{}]", start);

        Thread.sleep(random.nextInt(10000));

        long end = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
        log.info("==>  ExecutorAsyncTask taskTwo execute end [{}]", end);

        return new AsyncResult<>(end - start);
    }

    @Async(value = "taskExecutor")
    public Future<Long> taskThree() throws InterruptedException {
        long start = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
        log.info("==>  ExecutorAsyncTask taskThree execute start [{}]", start);

        Thread.sleep(random.nextInt(10000));

        long end = Instant.now(Clock.systemDefaultZone()).toEpochMilli();
        log.info("==>  ExecutorAsyncTask taskThree execute end [{}]", end);

        return new AsyncResult<>(end - start);
    }

}