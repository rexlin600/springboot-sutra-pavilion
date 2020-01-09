package xyz.rexlin600.java8.defaultmethod;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 16:53
 */
public class DefaultMethodTest {

    DefaultMethod.InnerDefaultMethod defaultMethod;

    @Before
    public void setUp() throws Exception {
        defaultMethod = new DefaultMethod.InnerDefaultMethod();
    }

    @Test
    public void hello() {
        defaultMethod.hello();
    }


}