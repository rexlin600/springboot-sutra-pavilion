package xyz.rexlin600.redis.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis string rest
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/redis/str")
public class RedisStringRest {

	/**
	 * STRING_KEY
	 */
	private static final String STRING_KEY = "STR_KEY:";

	/**
	 * Redis template
	 */
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Redis string rest
	 *
	 * @param redisTemplate redis template
	 */
	@Autowired
	public RedisStringRest(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * Add
	 */
	@PostMapping
	public void add() {
		// 普通 k-v
		redisTemplate.opsForValue().set(STRING_KEY + 1, Math.random());

		redisTemplate.opsForValue().set(STRING_KEY + 1, Math.random(), 1);

		redisTemplate.opsForValue().set(STRING_KEY + 2, Math.random());
		redisTemplate.opsForValue().set(STRING_KEY + 3, Math.random());
		redisTemplate.opsForValue().set(STRING_KEY + 4, Math.random());

		// 带过期时间的 k-v
		redisTemplate.opsForValue().set(STRING_KEY + 5, Math.random(), 10, TimeUnit.SECONDS);

		redisTemplate.opsForValue().set(STRING_KEY + 6, Math.random(), Duration.ofSeconds(10));
	}

	/**
	 * Del
	 */
	@DeleteMapping
	public void del() {
		// 删除单个key
		redisTemplate.delete(STRING_KEY + 1);

		// 批量删除key
		List<String> list = Arrays.asList(STRING_KEY + 2, STRING_KEY + 3, STRING_KEY + 4);
		redisTemplate.delete(list);
	}

	/**
	 * Increment
	 */
	@PostMapping("/incr")
	public void increment() {
		// 自增
		redisTemplate.opsForValue().increment(STRING_KEY + 7);

		// 自增指定步长 Long
		redisTemplate.opsForValue().increment(STRING_KEY + 8, 2L);

		// 自增指定步长 Double
		redisTemplate.opsForValue().increment(STRING_KEY + 9, 2.1);
	}

	/**
	 * Decrement
	 */
	@PostMapping("/decr")
	public void decrement() {
		// 自减
		redisTemplate.opsForValue().decrement(STRING_KEY + 10);

		redisTemplate.opsForValue().decrement(STRING_KEY + 11, 1L);
	}

}