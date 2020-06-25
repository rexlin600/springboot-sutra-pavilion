package xyz.rexlin600.redisson.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redisson 分布式锁
 *
 * @author rexlin600
 */
@Service
public class RedissonDistributedLocker implements DistributedLocker {

    /**
     * RedissonClient已经由配置类生成，这里自动装配即可
     */
    private final RedissonClient redissonClient;

    @Autowired
    public RedissonDistributedLocker(@Qualifier("redisson") RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * lock(), 拿不到lock就不罢休，不然线程就一直block
     *
     * @param lockKey
     * @return
     */
    @Override
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    /**
     * leaseTime为加锁时间，单位为秒
     *
     * @param lockKey
     * @param leaseTime
     * @return
     */
    @Override
    public RLock lock(String lockKey, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        return null;
    }

    /**
     * timeout为加锁时间，时间单位由unit确定
     *
     * @param lockKey
     * @param unit
     * @param timeout
     * @return
     */
    @Override
    public RLock lock(String lockKey, TimeUnit unit, long timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }

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
     * 释放锁
     *
     * @param lockKey
     */
    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    @Override
    public void unlock(RLock lock) {
        lock.unlock();
    }


}