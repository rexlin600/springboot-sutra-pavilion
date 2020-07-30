package xyz.rexlin600.common.util.repeat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * Redis 分布式锁实现，不依赖于 redisson 但是实际建议使用 redisson
 * 如有疑问可参考 @see <a href="https://www.cnblogs.com/linjiqin/p/8003838.html">Redis分布式锁的正确实现方式</a>
 *
 * @author hekunlin
 * @since 2020-07-30
 */
@Service
public class RedisLock {

	private static final Long RELEASE_SUCCESS = 1L;
	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	/**
	 * 当前设置 过期时间单位, EX = seconds; PX = milliseconds
	 */
	private static final String SET_WITH_EXPIRE_TIME = "EX";
	/**
	 * if get(key) == value return del(key)
	 */
	private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

	private final StringRedisTemplate redisTemplate;

	@Autowired
	public RedisLock(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 该加锁方法仅针对单实例 Redis 可实现分布式加锁
	 * 对于 Redis 集群则无法使用
	 * <p>
	 * 支持重复，线程安全
	 *
	 * @param lockKey  加锁键
	 * @param clientId 加锁客户端唯一标识(采用UUID)
	 * @param seconds  锁过期时间
	 * @return boolean
	 */
	@SuppressWarnings("ConstantConditions")
	public boolean tryLock(String lockKey, String clientId, long seconds) {
		return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
			Jedis jedis = (Jedis) redisConnection.getNativeConnection();
			String result = jedis.set(lockKey, clientId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds);
			return LOCK_SUCCESS.equals(result);
		});
	}

	/**
	 * 与 tryLock 相对应，用作释放锁
	 *
	 * @param lockKey  加锁键
	 * @param clientId 加锁客户端唯一标识
	 * @return boolean
	 */
	@SuppressWarnings("ConstantConditions")
	public boolean releaseLock(String lockKey, String clientId) {
		return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
			Jedis jedis = (Jedis) redisConnection.getNativeConnection();
			Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(lockKey),
					Collections.singletonList(clientId));
			return RELEASE_SUCCESS.equals(result);
		});
	}
}