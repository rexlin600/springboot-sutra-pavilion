package xyz.rexlin600.scheduler.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Scheduler task 2
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class SchedulerTask2 {

	/**
	 * PATTER
	 */
	public static final String PATTER = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Process 1
	 */
	@Scheduled(fixedRate = 6000)
	public void process1() {
		log.info("==>  process1 local datetime is [{}]", LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(PATTER)));
	}

	/**
	 * Process 2
	 */
	@Scheduled(fixedDelay = 12000)
	public void process2() {
		log.info("==>  process2 local datetime is [{}]", LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(PATTER)));
	}

}