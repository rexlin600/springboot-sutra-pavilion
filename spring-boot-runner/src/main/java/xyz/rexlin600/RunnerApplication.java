package xyz.rexlin600;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动时执行任务
 *
 * @author: hekunlin
 */
@SpringBootApplication
public class RunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunnerApplication.class, args);
    }

    /**
     * 从结果上可以看出如下几点：
     *
     * 1. ApplicationRunner 在相同优先级情况先先于 CommandLineRunner 执行
     * 2. ApplicationRunner 可以接收来自命令行的参数 args
     *
     * 参考文章：https://segmentfault.com/a/1190000018610015
     */

}