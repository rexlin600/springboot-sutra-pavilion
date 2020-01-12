package xyz.rexlin600.java8.date.api;

import org.junit.Before;
import org.junit.Test;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-10 11:29
 */
public class LocalDateTimeApiTest {

    LocalDateTimeApi localDateTimeApi;

    @Before
    public void setUp() throws Exception {
        localDateTimeApi = new LocalDateTimeApi();
    }

    @Test
    public void now() {
        localDateTimeApi.now();
    }

    @Test
    public void transfer() {
        localDateTimeApi.transfer();
    }

    @Test
    public void actual() {
        localDateTimeApi.actual();
    }

    @Test
    public void build() {
        localDateTimeApi.build();
    }

    @Test
    public void compare() {
        localDateTimeApi.compare();
    }
}