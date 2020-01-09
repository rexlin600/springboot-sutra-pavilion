package xyz.rexlin600.java8.functional.interfaces;

import org.junit.Before;
import org.junit.Test;
import xyz.rexlin600.model.Goods;

import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 14:53
 */
public class FunctionsTest {

    Functions functions;

    @Before
    public void setUp() throws Exception {
        functions = new Functions();
    }

    @Test
    public void plusOneHundredFunction() {
        Long result = functions.plusOneHundredFunction();

        assertEquals(110l, result.intValue());
    }

    @Test
    public void behavioralParameterization() {
        functions.behavioralParameterization((Function<Goods, String>) goods -> goods.getName().toString());
    }
}