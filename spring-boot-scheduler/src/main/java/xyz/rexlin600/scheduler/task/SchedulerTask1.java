package xyz.rexlin600.scheduler.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Scheduler task 1
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class SchedulerTask1 {

	/**
	 * PATTER
	 */
	public static final String PATTER = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Count
	 */
	private int count = 0;

	/**
	 * Process
	 */
	@Scheduled(cron = "*/30 * * * * ?")
	public void process() {
		log.info("==>  process this is scheduler task 1 runing, calculate count=[{}], local datetime is [{}]", count++, LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(PATTER)));
	}

}