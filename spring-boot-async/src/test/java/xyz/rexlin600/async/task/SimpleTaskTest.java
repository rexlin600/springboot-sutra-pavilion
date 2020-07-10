package xyz.rexlin600.async.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTaskTest {

	@Autowired
	private SimpleTask simpleTask;

	@Test
	public void taskOne() throws InterruptedException {
		simpleTask.taskOne();
	}

	@Test
	public void taskTwo() throws InterruptedException {
		// 这种方式和下面是一样的结果，task 顺序执行；这里涉及到的知识点：bean 的单例模式、classLoader、new 实例和 spring 帮我们创建实例的区别
		SimpleTask simpleTask = new SimpleTask();
		simpleTask.taskTwo();

		//simpleTask.taskTwo();
	}

	@Test
	public void taskThree() throws InterruptedException {
		simpleTask.taskThree();
	}

}