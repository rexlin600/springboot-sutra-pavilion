package xyz.rexlin600.redisson.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.redisson.client.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import xyz.rexlin600.redisson.lock.DistributedLocker;
import xyz.rexlin600.redisson.service.OrderService;

import java.util.concurrent.TimeUnit;

/**
 * Order service
 *
 * @author hekunlin
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	/**
	 * Distributed locker
	 */
	private final DistributedLocker distributedLocker;
	/**
	 * String redis template
	 */
	private final StringRedisTemplate stringRedisTemplate;

	/**
	 * Order service
	 *
	 * @param distributedLocker   distributed locker
	 * @param stringRedisTemplate string redis template
	 */
	@Autowired
	public OrderServiceImpl(DistributedLocker distributedLocker, StringRedisTemplate stringRedisTemplate) {
		this.distributedLocker = distributedLocker;
		this.stringRedisTemplate = stringRedisTemplate;
	}

	/**
	 * Book boolean
	 *
	 * @return the boolean
	 */
	@Override
	public boolean book() {
		String key = "lock";

		// get lock
		boolean lock = distributedLocker.tryLock(key, TimeUnit.MILLISECONDS, 300L, 500L);
		if (!lock) {
			throw new RedisException("系统繁忙，请稍后再试");
		}

		try {
			String value = this.stringRedisTemplate.opsForValue().get("count");
			Long count = Long.valueOf(value);
			if (count <= 0) {
				return false;
			} else {
				count = this.stringRedisTemplate.opsForValue().decrement("count");
			}

			log.info("==================库存还剩{}个================", count);

			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		} finally {
			distributedLocker.unlock(key);
		}
	}

}