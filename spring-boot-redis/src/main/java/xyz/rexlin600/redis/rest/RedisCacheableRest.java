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
 * Redis cacheable rest
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/redis/cacheable")
public class RedisCacheableRest {

	/**
	 * Cacheable *
	 *
	 * @param id id
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

		log.info("==>  blog is : {}", blog.toString());
	}

	/**
	 * Cache put *
	 *
	 * @param id id
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

		log.info("==>  blog is : {}", blog.toString());
	}

	/**
	 * Cache evict *
	 *
	 * @param id id
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

		log.info("==>  blog is : {}", blog.toString());
	}


}