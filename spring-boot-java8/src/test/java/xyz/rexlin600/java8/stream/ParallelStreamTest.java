package xyz.rexlin600.java8.stream;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 17:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParallelStreamTest {

    ParallelStream parallelStream;

    @Before
    public void setUp() throws Exception {
        parallelStream = new ParallelStream();
    }

    @Test
    public void parallelStream() {
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        List<Integer> integers = parallelStream.parallelStream(list);
        // 打印会发现 integers 的长度并不等于 10000
        System.out.println(integers.size());

        assertTrue(integers.size() == 10000);
        //assertFalse(integers.size() == 10000);
    }
}