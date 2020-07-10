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
 * Redistemplate rest
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/redis/crud")
public class RedisTemplateRest {

	/**
	 * HASH_KEY
	 */
	private static final String HASH_KEY = "BLOG_LIST";
	/**
	 * Redis template
	 */
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Redistemplate rest
	 *
	 * @param redisTemplate redis template
	 */
	@Autowired
	public RedisTemplateRest(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * Add *
	 *
	 * @param blog blog
	 */
	@PostMapping
	public void add(@RequestBody Blog blog) {
		redisTemplate.opsForHash().put(HASH_KEY, String.valueOf(blog.getId()), blog);
		log.info("==>  redis add val = [{}]", blog.toString());
	}


	/**
	 * Delete *
	 *
	 * @param id id
	 */
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		redisTemplate.opsForHash().delete(HASH_KEY, String.valueOf(id));
	}


	/**
	 * Update *
	 *
	 * @param blog blog
	 */
	@PutMapping
	public void update(@RequestBody Blog blog) {
		redisTemplate.opsForHash().delete(HASH_KEY, String.valueOf(blog.getId()));
		redisTemplate.opsForHash().putIfAbsent(HASH_KEY, String.valueOf(blog.getId()), blog);
	}

	/**
	 * Get *
	 *
	 * @param id id
	 */
	@GetMapping(value = "/{id}")
	public void get(@PathVariable(value = "id") Long id) {
		Blog blog = (Blog) redisTemplate.opsForHash().get(HASH_KEY, String.valueOf(id));
		log.info("==>  redis get val = [{}]", blog);
	}


	/**
	 * List
	 */
	@GetMapping(value = "/list")
	public void list() {
		Map<Object, Object> entries = redisTemplate.opsForHash().entries(HASH_KEY);
		log.info("==>  list entries is : {}", entries.toString());
	}


	/**
	 * Uid string
	 *
	 * @param session session
	 * @return the string
	 */
	@GetMapping(value = "/session")
	public String uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("id");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId();
	}


	/**
	 * Gets by annotation *
	 *
	 * @param id id
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
	 * Delete *
	 *
	 * @param key key
	 */
	@DeleteMapping(value = "/key/{key}")
	public void delete(@PathVariable(value = "key") String key) {
		redisTemplate.delete(key);
	}


}