package xyz.rexlin600;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SchedulerApplication 启动类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@EnableScheduling
@SpringBootApplication
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

}