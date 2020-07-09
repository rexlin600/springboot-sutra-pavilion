package xyz.rexlin600.scheduler.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * SchedulerTask1 类
 *
 * @author: hekunlin
 * @since: 2020/1/10
 */
@Slf4j
@Component
public class SchedulerTask1 {

    public static final String PATTER = "yyyy-MM-dd HH:mm:ss";

    private int count = 0;

    /**
     * 每 6 秒打印一次
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void process() {
        log.info("==>  process this is scheduler task 1 runing, calculate count=[{}], local datetime is [{}]", count++, LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(PATTER)));
    }

}