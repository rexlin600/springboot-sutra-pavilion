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
 * @date: 2020/1/10
 */
@Slf4j
@Component
public class SchedulerTask2 {

    public static final String PATTER = "yyyy-MM-dd HH:mm:ss";

    /**
     * 下面两种方式，程序启动时会执行一次，二者区别见参考文档的文章说明
     * <p>
     * fixedRate 说明
     *
     * @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
     * @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按 fixedRate 的规则每6秒执行一次
     */

    @Scheduled(fixedRate = 6000)
    public void process1() {
        log.info("==>  process1 local datetime is [{}]", LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(PATTER)));
    }

    @Scheduled(fixedDelay = 12000)
    public void process2() {
        log.info("==>  process2 local datetime is [{}]", LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(PATTER)));
    }

}