package xyz.rexlin600.elk.task;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The type Task service.
 *
 * @author rexlin600
 */
@Component
@Slf4j
public class TaskService {

	/**
	 * 生成DEBUG级别日志
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void generateDebugLog() {
		log.debug("==>  自动产生DEBUG级别日志，生产时间：{}", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
	}

	/**
	 * 生成INFO级别日志
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void generateInfoLog() {
		log.info("==>  自动产生INFO级别日志，生产时间：{}", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
	}

	/**
	 * 生成WARN级别日志
	 */
	@Scheduled(cron = "0/15 * * * * ?")
	public void generateWarnLog() {
		log.warn("==>  自动产生WARN级别日志，生产时间：{}", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
	}

	/**
	 * 生成ERROR级别日志
	 */
	@Scheduled(cron = "0/30 * * * * ?")
	public void generateErrorLog() {
		log.error("==>  自动产生ERROR级别日志，生产时间：{}", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
	}

	/**
	 * 生成ERROR
	 */
	@Scheduled(cron = "0/30 * * * * ?")
	public void generateError() {
		int errorRes = 1 / 0;
	}

}
