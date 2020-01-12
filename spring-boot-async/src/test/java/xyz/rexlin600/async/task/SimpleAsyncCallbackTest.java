package xyz.rexlin600.async.task;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleAsyncCallbackTest {

    @Autowired
    private SimpleAsyncCallback simpleAsyncCallback;

    @Test
    public void taskOne() throws InterruptedException, ExecutionException {
        Future<Long> future = simpleAsyncCallback.taskOne();
        // 获取异步回调，注意使用 future 的 get 方法会阻塞！！！
        Long executeTime = future.get();

        if (future.isDone()) {
            log.info("==>  future is done, callback value is [{}]", executeTime);
        }
    }

    @Test
    public void taskTwo() throws InterruptedException, ExecutionException {
        Future<Long> future = simpleAsyncCallback.taskTwo();
        // 获取异步回调，模拟 TimeoutException（因为某些场景我们不可能一直等待回调，这样会阻塞线程）
        Long executeTime = null;
        try {
            executeTime = future.get(500, TimeUnit.MICROSECONDS);
        } catch (TimeoutException e) {
            log.info("==>  future is done, expire");
        }

        // 因为回调超时，所以这里为 null
        log.info("==>  future is done, assign expire time, callback value is [{}]", executeTime);
    }
}