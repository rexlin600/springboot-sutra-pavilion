package xyz.rexlin600.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-10 9:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTest {

    @Test
    public void main() {
        System.out.println("Test Hello World main...");
    }
}