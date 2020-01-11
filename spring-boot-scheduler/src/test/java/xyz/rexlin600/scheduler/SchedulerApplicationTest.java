package xyz.rexlin600.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-10 11:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchedulerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchedulerApplicationTest {

    @Test
    public void contextLoads() {
    }

}