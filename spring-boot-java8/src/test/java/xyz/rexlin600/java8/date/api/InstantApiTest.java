package xyz.rexlin600.java8.date.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-10 11:28
 */
public class InstantApiTest {

    InstantApi instantApi;

    @Before
    public void setUp() throws Exception {
        instantApi = new InstantApi();
    }

    @Test
    public void now() {
        instantApi.now();
    }

    @Test
    public void mill() {
        instantApi.mill();
    }

    @Test
    public void build() {
        instantApi.build();
    }

    @Test
    public void compare() {
        instantApi.compare();
    }
}