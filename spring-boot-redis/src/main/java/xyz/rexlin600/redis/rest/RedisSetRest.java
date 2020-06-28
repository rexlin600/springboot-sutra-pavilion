package xyz.rexlin600.redis.rest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Redist Set Rest
 *
 * @author: hekunlin
 * @date: 2020/6/28
 */
@Slf4j
@RestController
@RequestMapping("/redis/set")
public class RedisSetRest {

    private final static String SET_KEY = "SET_KEY";

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisSetRest(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * store
     */
    @PostMapping
    public void store() {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

        // add
        opsForSet.add(SET_KEY, 1, 1, 2, 3, 4, "12", 12.1231, "ABC");
        opsForSet.add(SET_KEY + 1, 1, 2, 99);

        // 取出差集并存储；交集、并集存储类似，这里不再演示
        // 比较前面两个 key 的 set 数据并将差值部分的数据保存到 dest_key 对应的 set 中
        opsForSet.differenceAndStore(SET_KEY, SET_KEY + 1, SET_KEY);

        // 下面这种方式可以实现和多个 SET 进行对比
        List<String> list = Arrays.asList(SET_KEY + 1);
        opsForSet.differenceAndStore(SET_KEY, list, SET_KEY);
    }

    /**
     * difference
     * <p>
     * 差集
     */
    @GetMapping("/diff")
    public void diff() {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

        // init data
        opsForSet.add(SET_KEY, 1, 1, 2, 3, 4, "12", 12.1231, "ABC");
        opsForSet.add(SET_KEY + 1, 1, 2, 99);

        Set<Object> set1 = opsForSet.difference(SET_KEY, SET_KEY + 1);

        List<String> list = Arrays.asList(SET_KEY + 1);
        Set<Object> set2 = opsForSet.difference(SET_KEY, list);

        log.info("==>  set1 and set2 is equal? {}", set1.equals(set2));
    }

    /**
     * intersect
     * <p>
     * 交集
     */
    @GetMapping("/intersect")
    public void intersect() {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

        // init data
        opsForSet.add(SET_KEY, 1, 1, 2, 3, 4, "12", 12.1231, "ABC");
        opsForSet.add(SET_KEY + 1, 1, 2, 99);

        // 交集
        Set<Object> set1 = opsForSet.intersect(SET_KEY, SET_KEY + 1);

        List<String> list = Arrays.asList(SET_KEY + 1);
        Set<Object> set2 = opsForSet.intersect(SET_KEY, list);

        log.info("==>  set1 and set2 is equal? {}", set1.equals(set2));
    }

    /**
     * union
     * <p>
     * 并集
     */
    @GetMapping("/union")
    public void union() {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

        // init data
        opsForSet.add(SET_KEY, 1, 1, 2, 3, 4, "12", 12.1231, "ABC");
        opsForSet.add(SET_KEY + 1, 1, 2, 99);

        Set<Object> set1 = opsForSet.union(SET_KEY, SET_KEY + 1);

        List<String> list = Arrays.asList(SET_KEY + 1);
        Set<Object> set2 = opsForSet.union(SET_KEY, list);

        log.info("==>  set1 and set2 is equal? {}", set1.equals(set2));
    }

    /**
     * member
     */
    @GetMapping("/member")
    public void member() {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

        // init data
        opsForSet.add(SET_KEY, 1, 1, 2, 3, 4, "12", 12.1231, "ABC");

        // 某个 value 是否存在于 set 中
        Integer value = 1;
        Boolean isMember = opsForSet.isMember(SET_KEY, value);
        log.info("==>  value {} is SET member {}", value, isMember);

        // 取出 Set 所有成员
        Set<Object> set1 = opsForSet.members(SET_KEY);
        log.info("==>  set member is : {}", set1.toString());

        // 取出 Set 中 count 个随机元素
        Set<Object> set2 = opsForSet.distinctRandomMembers(SET_KEY, 2);
        log.info("==>  set member is : {}", set2.toString());
    }

    /**
     * pop
     * <p>
     * 注意：pop 后 set 中将会移除 pop 的数据
     */
    @GetMapping("/pop")
    public void pop() {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

        // init data
        opsForSet.add(SET_KEY, 1, 1, 2, 3, 4, "12", 12.1231, "ABC");

        // 从 set 中弹出一个元素
        Object value = opsForSet.pop(SET_KEY);
        log.info("==>  set pop value is : {}", value);

        List<Object> list = opsForSet.pop(SET_KEY, 2);
        log.info("==>  set pop value is : {}", list.toString());
    }

    /**
     * scan
     * <p>
     * 浏览
     */
    @SneakyThrows
    @GetMapping("/scan")
    public void scan() {
        Cursor<Object> cursor = null;
        try {
            SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

            // init data
            opsForSet.add(SET_KEY, 1, 1, 2, 3, 4, "12", 12.1231, "ABC");

            ScanOptions.ScanOptionsBuilder optionsBuilder = new ScanOptions.ScanOptionsBuilder();
            ScanOptions options = optionsBuilder.count(3)
                    .match("*1*")
                    .build();
            cursor = opsForSet.scan(SET_KEY, options);
            long position = cursor.getPosition();
            long cursorId = cursor.getCursorId();

            log.info("==>  positionId : {} cursorId : {}", position, cursorId);

            // forEach 与 while 循环只能 二选一，因为随着循环 cursor 会 finished，后续无法再次遍历，因为这时候的 cursor 已经处于 FINISHED
            cursor.forEachRemaining(m -> {
                log.info("==>  foreach value is : {}", m);
            });

            // while 循环不会执行
            while (cursor.hasNext()) {
                Object next = cursor.next();
                log.info("==>  scan value is : {}", next);
            }

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                // 避免资源泄露
                cursor.close();
            }
        }
    }


}