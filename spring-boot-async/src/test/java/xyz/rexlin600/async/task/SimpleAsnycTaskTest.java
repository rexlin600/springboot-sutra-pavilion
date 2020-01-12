package xyz.rexlin600.async.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleAsnycTaskTest {

    @Autowired
    private SimpleAsnycTask simpleAsnycTask;

    /**
     * 可以反复执行单元测试，您可能会遇到各种不同的结果，比如：
     * <p>
     * 1. 无输出
     * 2. 部分输出
     * 3. 乱序
     */

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