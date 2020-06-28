package xyz.rexlin600.redisson.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import xyz.rexlin600.redisson.lock.DistributedLocker;
import xyz.rexlin600.redisson.service.OrderService;

import java.util.concurrent.TimeUnit;

/**
 * 订单服务实现类
 *
 * @author: rexlin600
 * @date: 2020-02-05
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final DistributedLocker distributedLocker;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public OrderServiceImpl(DistributedLocker distributedLocker, StringRedisTemplate stringRedisTemplate) {
        this.distributedLocker = distributedLocker;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean book() {
        String key = "lock";

        // get lock
        boolean lock = distributedLocker.tryLock(key, TimeUnit.MILLISECONDS, 300L, 500L);
        if (!lock) {
            throw new RuntimeException("系统繁忙，请稍后再试");
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