package xyz.rexlin600.redisson.lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Distributed locker
 *
 * @author hekunlin
 */
public interface DistributedLocker {

	/**
	 * Lock r lock
	 *
	 * @param lockKey lock key
	 * @return the r lock
	 */
	RLock lock(String lockKey);

	/**
	 * Lock r lock
	 *
	 * @param lockKey lock key
	 * @param timeout timeout
	 * @return the r lock
	 */
	RLock lock(String lockKey, long timeout);

	/**
	 * Lock r lock
	 *
	 * @param lockKey lock key
	 * @param unit    unit
	 * @param timeout timeout
	 * @return the r lock
	 */
	RLock lock(String lockKey, TimeUnit unit, long timeout);

	/**
	 * Try lock boolean
	 *
	 * @param lockKey   lock key
	 * @param unit      unit
	 * @param waitTime  wait time
	 * @param leaseTime lease time
	 * @return the boolean
	 */
	boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

	/**
	 * Unlock *
	 *
	 * @param lockKey lock key
	 */
	void unlock(String lockKey);

	/**
	 * Unlock *
	 *
	 * @param lock lock
	 */
	void unlock(RLock lock);

}
