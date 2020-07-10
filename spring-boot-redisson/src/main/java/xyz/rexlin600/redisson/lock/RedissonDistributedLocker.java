package xyz.rexlin600.redisson.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redisson distributed locker
 *
 * @author hekunlin
 */
@Service
public class RedissonDistributedLocker implements DistributedLocker {

	/**
	 * Redisson client
	 */
	private final RedissonClient redissonClient;

	/**
	 * Redisson distributed locker
	 *
	 * @param redissonClient redisson client
	 */
	@Autowired
	public RedissonDistributedLocker(@Qualifier("redisson") RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	/**
	 * Lock r lock
	 *
	 * @param lockKey lock key
	 * @return the r lock
	 */
	@Override
	public RLock lock(String lockKey) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock();
		return lock;
	}

	/**
	 * Lock r lock
	 *
	 * @param lockKey   lock key
	 * @param leaseTime lease time
	 * @return the r lock
	 */
	@Override
	public RLock lock(String lockKey, long leaseTime) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock(leaseTime, TimeUnit.SECONDS);
		return null;
	}

	/**
	 * Lock r lock
	 *
	 * @param lockKey lock key
	 * @param unit    unit
	 * @param timeout timeout
	 * @return the r lock
	 */
	@Override
	public RLock lock(String lockKey, TimeUnit unit, long timeout) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock(timeout, unit);
		return lock;
	}

	/**
	 * Try lock boolean
	 *
	 * @param lockKey   lock key
	 * @param unit      unit
	 * @param waitTime  wait time
	 * @param leaseTime lease time
	 * @return the boolean
	 */
	@Override
	public boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
		RLock lock = redissonClient.getLock(lockKey);
		try {
			return lock.tryLock(waitTime, leaseTime, unit);
		} catch (InterruptedException e) {
			return false;
		}
	}

	/**
	 * Unlock *
	 *
	 * @param lockKey lock key
	 */
	@Override
	public void unlock(String lockKey) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.unlock();
	}

	/**
	 * Unlock *
	 *
	 * @param lock lock
	 */
	@Override
	public void unlock(RLock lock) {
		lock.unlock();
	}


}