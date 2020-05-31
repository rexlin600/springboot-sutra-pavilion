package xyz.rexlin600.redisson.lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

public interface DistributedLocker {

    /**
     * 获取锁
     *
     * @param lockKey
     * @return
     */
    RLock lock(String lockKey);

    /**
     * 获取锁
     *
     * @param lockKey
     * @param timeout
     * @return
     */
    RLock lock(String lockKey, long timeout);

    /**
     * 获取锁
     *
     * @param lockKey
     * @param unit
     * @param timeout
     * @return
     */
    RLock lock(String lockKey, TimeUnit unit, long timeout);

    /**
     * 获取锁
     *
     * @param lockKey
     * @param unit
     * @param waitTime
     * @param leaseTime
     * @return
     */
    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    /**
     * 释放锁
     *
     * @param lockKey
     */
    void unlock(String lockKey);

    /**
     * 释放锁
     *
     * @param lock
     */
    void unlock(RLock lock);

}
