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