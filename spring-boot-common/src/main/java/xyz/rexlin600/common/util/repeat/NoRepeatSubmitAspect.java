package xyz.rexlin600.common.util.repeat;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 防重复提交AOP
 *
 * @author hekunlin
 * @since 2020 -7-30
 */
@Slf4j
@Aspect
@Component
public class NoRepeatSubmitAspect {

	/**
	 * 分布式锁，可替换为 redisson
	 */
	private final RedisLock redisLock;

	/**
	 * Instantiates a new No repeat submit aspect.
	 *
	 * @param redisLock the redis lock
	 */
	@Autowired
	public NoRepeatSubmitAspect(RedisLock redisLock) {
		this.redisLock = redisLock;
	}

	/**
	 * Point cut.
	 *
	 * @param noRepeatSubmit the no repeat submit
	 */
	@Pointcut(value = "@annotation(noRepeatSubmit)", argNames = "noRepeatSubmit")
	public void pointCut(NoRepeatCommit noRepeatSubmit) {
	}

	/**
	 * Around object.
	 *
	 * @param pjp            the pjp
	 * @param noRepeatSubmit the no repeat submit
	 * @return the object
	 * @throws Throwable the throwable
	 */
	@Around(value = "pointCut(noRepeatSubmit)", argNames = "pjp,noRepeatSubmit")
	public Object around(ProceedingJoinPoint pjp, NoRepeatCommit noRepeatSubmit) throws Throwable {
		long lockSeconds = noRepeatSubmit.lockTime();

		HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestAttributes();
		Assert.notNull(request, "request can not null");

		// 此处可以用token或者JSessionId
		String token = request.getHeader("Authorization");
		String path = request.getServletPath();
		String key = getKey(token, path);
		String clientId = getClientId();

		boolean isSuccess = redisLock.tryLock(key, clientId, lockSeconds);
		log.info("tryLock key = [{}], clientId = [{}]", key, clientId);

		if (isSuccess) {
			log.info("tryLock success, key = [{}], clientId = [{}]", key, clientId);
			// 获取锁成功
			Object result;

			try {
				// 执行进程
				result = pjp.proceed();
			} finally {
				// 解锁
				redisLock.releaseLock(key, clientId);
				log.info("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
			}

			return result;

		} else {
			// 获取锁失败，认为是重复提交的请求
			log.info("tryLock fail, key = [{}]", key);
			return "重复请求，请稍后再试";
		}

	}

	/**
	 * 获取 key
	 *
	 * @param token token
	 * @param path  path
	 * @return String
	 */
	private String getKey(String token, String path) {
		return token + path;
	}

	/**
	 * 获取 clientId
	 *
	 * @return String
	 */
	private String getClientId() {
		return UUID.randomUUID().toString();
	}

}
