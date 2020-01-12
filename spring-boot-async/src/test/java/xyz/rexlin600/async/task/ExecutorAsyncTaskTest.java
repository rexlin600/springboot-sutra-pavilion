package xyz.rexlin600.async.task;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutorAsyncTaskTest {

    @Autowired
    private ExecutorAsyncTask executorAsyncTask;

    /**
     * {@link Future} 接口定义了下面 5 个方法：
     * cancel 方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false。参数mayInterruptIfRunning表示是否允许取消正在执行却没有执行完毕的任务，如果设置true，则表示可以取消正在执行过程中的任务。如果任务已经完成，则无论mayInterruptIfRunning为true还是false，此方法肯定返回false，即如果取消已经完成的任务会返回false；如果任务正在执行，若mayInterruptIfRunning设置为true，则返回true，若mayInterruptIfRunning设置为false，则返回false；如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true。
     * isCancelled 方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。
     * isDone 方法表示任务是否已经完成，若任务完成，则返回true；
     * get() 方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；（注意：会产生阻塞！！！）
     * get (long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。
     *
     *
     * <p>
     * 也就是说 Future 提供了三种功能：
     * <p>
     * 1. 判断任务是否完成；
     * 2. 能够中断任务；
     * 3. 能够获取任务执行结果。
     * <p>
     * <p>
     * 下面的测试方法会发现：
     * 1. taskOne、taskTwo|taskthree 开始执行
     * 2. taskOne 完成（可能在 taskTwo|taksThree 之前或之后）；
     * 3. taskTwo 执行时 taskThree 会被阻塞直到 taskTwo 完成（反之亦然）
     */

    @Test
    public void taskOne() throws InterruptedException, ExecutionException {
        executorAsyncTask.taskOne();
    }

    @Test
    public void taskTwo() throws InterruptedException, ExecutionException {
        Future<Long> future = executorAsyncTask.taskTwo();
        Long value = future.get();
        log.info("==>  taskTwo execute success, value is [{}]", value);
    }

    @Test
    public void taskThree() throws InterruptedException, ExecutionException {
        Future<Long> future = executorAsyncTask.taskThree();
        Long value = future.get();
        log.info("==>  taskThree execute success, value is [{}]", value);
    }
}