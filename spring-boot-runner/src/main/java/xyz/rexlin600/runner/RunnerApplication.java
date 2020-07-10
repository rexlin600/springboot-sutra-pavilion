package xyz.rexlin600.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runner application
 *
 * @author hekunlin
 */
@SpringBootApplication
public class RunnerApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
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