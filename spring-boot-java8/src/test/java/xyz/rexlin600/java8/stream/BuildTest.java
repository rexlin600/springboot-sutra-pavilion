package xyz.rexlin600.java8.stream;

import org.junit.Before;
import org.junit.Test;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 10:47
 */
public class BuildTest {

    Build build;

    @Before
    public void setUp() throws Exception {
        build = new Build();
    }

    @Test
    public void buildStream() {
        build.buildStream();
    }
}