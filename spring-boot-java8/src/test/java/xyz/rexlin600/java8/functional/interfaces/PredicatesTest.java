package xyz.rexlin600.java8.functional.interfaces;

import org.junit.Before;
import org.junit.Test;
import xyz.rexlin600.java8.functional.interfaces.Predicates;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 10:44
 */
public class PredicatesTest {

    Predicates predicates;

    @Before
    public void setUp() throws Exception {
        predicates = new Predicates();
    }

    @Test
    public void judgeNumberOne() {
        boolean max = predicates.judgeNumberOne(1000);
        boolean min = predicates.judgeNumberOne(10);

        assertTrue(max);
        assertFalse(min);
    }

    @Test
    public void judgeNumberTwo() {
        boolean max = predicates.judgeNumberTwo(1000);
        boolean min = predicates.judgeNumberTwo(10);

        assertTrue(max);
        assertFalse(min);
    }

    @Test
    public void judgeNumberNegate() {
        boolean max = predicates.judgeNumberNegate(1000);
        boolean min = predicates.judgeNumberNegate(10);

        assertFalse(max);
        assertTrue(min);

    }
}