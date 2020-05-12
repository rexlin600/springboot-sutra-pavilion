package xyz.rexlin600.redis.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.redis.entity.Blog;

/**
 * Redis Cacheable API
 * <p>
 * Spring 提供的 cahce 注解使用，参考文章：https://blog.csdn.net/dreamhai/article/details/80642010
 *
 * @author: hekunlin
 * @date: 2020/5/11
 */
@Slf4j
@RestController
@RequestMapping("/redis/cacheable")
public class RedisCacheableRest {

    /**
     * 1. Cacheable
     * <p>
     * condition：满足xx才缓存。满足缓存条件的数据才会放入缓存，condition在调用方法之前和之后都会判断，用于参数判断
     * unless：除非xx才缓存。用于否决缓存更新的(比如如果需要缓存结果则要写为 #result == null)，不像condition，该表达只在方法执行之后判断，此时可以拿到返回值result进行判断了，用于返回结果判断
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "blog-list", key = "#id", unless = "#result == null", condition = "#id > 1L")
    @GetMapping(value = "/cacheable/{id}")
    public void cacheable(@PathVariable(value = "id") Long id) {

        // 模拟查询数据库
        Blog blog = Blog.builder()
                .id(999L)
                .title("999")
                .content("999")
                .popular(true)
                .createDate("2020-1-6 15:46:03")
                .build();
    }

    /**
     * 2. CachePut
     *
     * @param id
     * @return
     */
    @CachePut(cacheNames = "blog-list", key = "#id")
    @GetMapping(value = "/cachePut/{id}")
    public void cachePut(@PathVariable(value = "id") Long id) {

        // 模拟查询数据库
        Blog blog = Blog.builder()
                .id(999L)
                .title("update-999")
                .content("update-999")
                .popular(true)
                .createDate("2020-1-6 15:46:03")
                .build();
    }

    /**
     * 3. cacheEvict
     *
     * @param id
     * @return
     */
    @CacheEvict(cacheNames = "blog-list", key = "#id")
    @GetMapping(value = "/cacheEvict/{id}")
    public void cacheEvict(@PathVariable(value = "id") Long id) {

        // 模拟查询数据库
        Blog blog = Blog.builder()
                .id(999L)
                .title("999")
                .content("999")
                .popular(true)
                .createDate("2020-1-6 15:46:03")
                .build();
    }


}