package xyz.rexlin600.java8.stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.java8.model.Goods;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 14:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReduceTest {

    private Reduce reduce;

    @Before
    public void setUp() throws Exception {
        reduce = new Reduce();
    }

    @Test
    public void reduce() {
        Goods goods = reduce.reduce();

        assertEquals("Juan", goods.getName());
    }

    @Test
    public void reduce1() {
        Goods reduce = this.reduce.reduceByIdentity();
        System.out.println(reduce);

    }
}