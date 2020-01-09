package xyz.rexlin600.java8.stream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 10:47
 */
public class CountTest {

    Count count;

    @Before
    public void setUp() throws Exception {
        count = new Count();
    }

    @Test
    public void listItemCount() {
        Long itemCount = count.listItemCount();

        assertEquals(10l, itemCount.longValue());
    }

    @Test
    public void listItemDetailCount() {
        Long listItemDetailCount = count.listItemDetailCount();

        assertEquals(3l, listItemDetailCount.longValue());
    }

    @Test
    public void streamOfCount() {
        Long streamOfCount = count.streamOfCount();

        assertEquals(1l, streamOfCount.longValue());
    }
}