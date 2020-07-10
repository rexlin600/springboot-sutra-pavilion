package xyz.rexlin600.lo4j;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Log 4 j application
 *
 * @author hekunlin
 */
@Slf4j
@SpringBootApplication
public class Log4jApplication {

	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(Log4jApplication.class.getName());

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		logger.info("log4j 初始化项目 ...");

		// 以下日志不会被打印到控制台，因为 @slf4j 并没有具体的实现；我们可以打开 pom.xml 中的 spring-boot-starter-logging 的注释即可看到下面的日志会被打印，且打印日志在IDE中会变成彩色
		log.info("log 初始化项目 ...");

		logger.error("log4j error 初始化项目 ...");
		log.error("log error 初始化项目 ...");
		SpringApplication.run(Log4jApplication.class, args);
	}

}