package xyz.rexlin600.scheduler.task;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.scheduler.SchedulerApplication;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchedulerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchedulerApplicationTest {

	@Test
	public void contextLoads() {
		log.info("==>  SchedulerApplicationTest ...");
	}

}