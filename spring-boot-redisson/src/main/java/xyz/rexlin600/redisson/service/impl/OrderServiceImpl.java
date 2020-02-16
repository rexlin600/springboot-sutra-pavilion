package xyz.rexlin600.redisson.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import xyz.rexlin600.redisson.service.OrderService;

import java.util.concurrent.TimeUnit;

/**
 * 订单服务实现类
 *
 * @author: rexlin600
 * @date: 2020-02-05
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public OrderServiceImpl(RedissonClient redissonClient, StringRedisTemplate stringRedisTemplate) {
        this.redissonClient = redissonClient;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean book() {
        // get lock
        RLock lock = this.redissonClient.getLock("lock");
        try {
            lock.lock(10, TimeUnit.SECONDS);
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
            lock.unlock();
        }
    }

}