package xyz.rexlin600.redis.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.redis.entity.Blog;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * @menu Redis服务API
 * @author: hekunlin
 * @date: 2020/1/6
 */
@Slf4j
@RestController
@RequestMapping("/redis/crud")
public class RedistemplateRest {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String HASH_KEY = "BLOG_LIST";

    @Autowired
    public RedistemplateRest(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 1. 【新增blog】
     *
     * @param blog
     * @return
     */
    @PostMapping
    public void add(@RequestBody Blog blog) {
        redisTemplate.opsForHash().put(HASH_KEY, String.valueOf(blog.getId()), blog);
        log.info("==>  redis add val = [{}]", blog.toString());
    }


    /**
     * 2. 【删除blog】
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        redisTemplate.opsForHash().delete(HASH_KEY, String.valueOf(id));
    }


    /**
     * 3. 【修改blog】
     *
     * @param blog
     */
    @PutMapping
    public void update(@RequestBody Blog blog) {
        redisTemplate.opsForHash().delete(HASH_KEY, String.valueOf(blog.getId()));
        redisTemplate.opsForHash().putIfAbsent(HASH_KEY, String.valueOf(blog.getId()), blog);
    }

    /**
     * 4. 【获取blog】
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public void get(@PathVariable(value = "id") Long id) {
        Blog blog = (Blog) redisTemplate.opsForHash().get(HASH_KEY, String.valueOf(id));
        log.info("==>  redis get val = [{}]", blog);
    }


    /**
     * 5. 【获取blog list】
     *
     * @return
     */
    @GetMapping(value = "/list")
    public void list() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(HASH_KEY);
        log.info("==>  list entries is : {}", entries.toString());
    }


    /**
     * 6. 【redis实现共享session】
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/session")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("id");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }


    /**
     * 7. 【获取blog_cacheable】
     *
     * @param id
     * @return
     */
    @Cacheable(value = "blog-list", key = "#id")
    @GetMapping(value = "/annotation/{id}")
    public void getByAnnotation(@PathVariable(value = "id") Long id) {

        // 模拟查询数据库
        Blog blog = Blog.builder()
                .id(999L)
                .title("999")
                .content("999")
                .popular(true)
                .createDate("2020-1-6 15:46:03")
                .build();
        log.info("==>  db get val = [{}]", blog);
    }


    /**
     * 7. 【删除指定的key】
     *
     * @param key
     * @return
     */
    @DeleteMapping(value = "/key/{key}")
    public void delete(@PathVariable(value = "key") String key) {
        redisTemplate.delete(key);
    }


}