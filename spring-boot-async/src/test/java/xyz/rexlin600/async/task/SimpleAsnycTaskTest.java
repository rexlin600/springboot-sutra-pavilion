package xyz.rexlin600.async.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleAsnycTaskTest {

	@Autowired
	private SimpleAsnycTask simpleAsnycTask;

	@Test
	public void taskOne() throws InterruptedException {
		simpleAsnycTask.taskOne();
	}

	@Test
	public void taskTwo() throws InterruptedException {
		simpleAsnycTask.taskTwo();
	}

	@Test
	public void taskThree() throws InterruptedException {
		simpleAsnycTask.taskThree();
	}

}