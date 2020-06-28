package xyz.rexlin600.redis.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis List 数据结构使用
 *
 * @author: hekunlin
 * @date: 2020/6/28
 */
@Slf4j
@RestController
@RequestMapping("/redis/list")
public class RedisListRest {

    private static final String LIST_KEY = "LIST_KEY";

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisListRest(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * left push 相关的方法
     * <p>
     * right 原理类似，这里就不一一演示了
     */
    @PostMapping
    public void push() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();

        // left：每次从列表的左侧开始新增
        int i = (int) (Math.random() * 1000);
        opsForList.leftPush(LIST_KEY, i);

        // 通过leftPush(K key, V pivot, V value)方法把 值 放到指定参数值前面
        opsForList.leftPush(LIST_KEY, i, (int) (Math.random() * 1000));

        // 批量 push
        opsForList.leftPushAll(LIST_KEY, Arrays.asList(1, 2, 3));
        opsForList.leftPushAll(LIST_KEY, 4, 5, 6);

        // leftPushIfPresent，只有当 list 存在时才会执行 Push 操作，所以下面第一条语句实际上不会有效果
        opsForList.leftPushIfPresent(LIST_KEY + 1, (int) (Math.random() * 1000));
        opsForList.leftPushIfPresent(LIST_KEY, 2000);
    }

    /**
     * left pop 相关的方法
     * <p>
     * right 原理类似，这里就不一一演示了
     */
    @GetMapping
    public void pop() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();

        // 弹出
        Object pop1 = opsForList.leftPop(LIST_KEY);
        log.info("==>  pop value is {}", pop1);

        // 弹出后阻断连接 或 指定时间范围（不管是否弹出）阻断连接
        Object pop2 = opsForList.leftPop(LIST_KEY, 5, TimeUnit.SECONDS);
        log.info("==>  pop value is {}", pop2);
    }

    /**
     * range
     */
    @GetMapping("/range")
    public void range() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();

        // init data
        opsForList.leftPushAll(LIST_KEY, Arrays.asList(1, 2, 3));

        // 这里会返回 list 的从左开始数，前面 3 个数据
        List<Object> list1 = opsForList.range(LIST_KEY, 0, 2);
        log.info("==>  range list is : {}", list1.toString());

        // 返回从 偏移量为 0 到偏移量为 -1（即全部数据）
        List<Object> list2 = opsForList.range(LIST_KEY, 0, -1);
        log.info("==>  range list is : {}", list2.toString());

        // 读取 List 的 size
        Long size = opsForList.size(LIST_KEY);
        log.info("==>  list size is : {}", size);
    }

    /**
     * index
     */
    @GetMapping("/index")
    public void index() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();

        // 从 List 获取指定 index 的数据
        int index = 0;
        Object value = opsForList.index(LIST_KEY, index);

        log.info("==>  index {} value is : {}", index, value);
    }

    /**
     * remove
     */
    @DeleteMapping
    public void remove() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();

        // 从 List 获取指定 index 的数据
        int index = 0;
        Object value = opsForList.index(LIST_KEY, index);

        // 删除 count 个在 List 中匹配 value 的数据
        opsForList.remove(LIST_KEY, 1, value);

        log.info("==>  index {} value is : {}", index, value);
    }

    /**
     * trim
     */
    @PostMapping("/trim")
    public void trim() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();

        // init data
        opsForList.leftPushAll(LIST_KEY, Arrays.asList(1, 2, 3));

        // 修剪：只保留 index从 1 开始的所有数据
        opsForList.trim(LIST_KEY, 1, -1);

        List<Object> list = opsForList.range(LIST_KEY, 0, -1);
        log.info("==>  trim value is : {}", list.toString());
    }


}