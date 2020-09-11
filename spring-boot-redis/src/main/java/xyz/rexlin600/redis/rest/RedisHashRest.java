package xyz.rexlin600.redis.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Redis Hash接口
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/redis/hash")
public class RedisHashRest {

	/**
	 * REDIS_KEY
	 */
	private static final String REDIS_KEY = "HASH_KEY";

	/**
	 * Redis template
	 */
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Redis hash rest
	 *
	 * @param redisTemplate redis template
	 */
	@Autowired
	public RedisHashRest(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 更新
	 */
	@PostMapping
	public void put() {
		// put
		redisTemplate.opsForHash().put(REDIS_KEY, "name", "kobe");
		redisTemplate.opsForHash().put(REDIS_KEY, "name", "rexlin600");

		// put All
		HashMap<String, Object> hashMap = init();
		redisTemplate.opsForHash().putAll("HASH_KEY2", hashMap);

		// put IfAbsent：不会成功
		redisTemplate.opsForHash().putIfAbsent(REDIS_KEY, "name", "james");
		// put IfAbsent：成功，因为 #putIfAbsent 方法只有当 hashKey 不存在时才会成功
		redisTemplate.opsForHash().putIfAbsent(REDIS_KEY + "1", "firstname", "james");
	}

	/**
	 * 删除
	 */
	@DeleteMapping
	public void delete() {
		// 删除部分 hashKey
		redisTemplate.opsForHash().delete(REDIS_KEY + "2", "name");
	}

	/**
	 * 遍历
	 */
	@GetMapping("/entries")
	public void entries() {
		// init
		HashMap<String, Object> hashMap = init();
		redisTemplate.opsForHash().putAll(REDIS_KEY, hashMap);

		// 获取某个 key 下的 hashMap 数据
		Map<Object, Object> entries = redisTemplate.opsForHash().entries(REDIS_KEY);
		log.info("==>  entries size : {}", entries.size());
	}

	/**
	 * 读取
	 */
	@GetMapping
	public void get() {
		// init
		HashMap<String, Object> hashMap = init();
		redisTemplate.opsForHash().putAll(REDIS_KEY, hashMap);

		String name = (String) redisTemplate.opsForHash().get(REDIS_KEY, "name");
		Integer age = (Integer) redisTemplate.opsForHash().get(REDIS_KEY, "age");

		log.info("==>  get hash value : {}", name);
		log.info("==>  get hash value : {}", age);
	}


	/**
	 * 判断是否存在Key
	 */
	@GetMapping("/hasKey")
	public void hasKey() {
		// init
		HashMap<String, Object> hashMap = init();
		redisTemplate.opsForHash().putAll(REDIS_KEY, hashMap);

		Boolean hasKey1 = redisTemplate.opsForHash().hasKey(REDIS_KEY, "name");
		log.info("==>  has key result: {}", hasKey1);

		Boolean hasKey2 = redisTemplate.opsForHash().hasKey(REDIS_KEY, "ggg");
		log.info("==>  has key result: {}", hasKey2);
	}

	/**
	 * 自增
	 */
	@PostMapping("/incr")
	public void increment() {
		// incr long
		redisTemplate.opsForHash().increment(REDIS_KEY + "1", "COUNT1", 1L);

		// incr double
		redisTemplate.opsForHash().increment(REDIS_KEY + "2", "COUNT2", 1.2D);

		// init
		HashMap<String, Object> hashMap = init();
		redisTemplate.opsForHash().putAll(REDIS_KEY + "3", hashMap);
		redisTemplate.opsForHash().increment(REDIS_KEY + "3", "age", 1L);
	}

	/**
	 * 获取所有Key
	 */
	@GetMapping("/keys")
	public void keys() {
		// init
		HashMap<String, Object> hashMap = init();
		redisTemplate.opsForHash().putAll(REDIS_KEY, hashMap);

		Set<Object> keys = redisTemplate.opsForHash().keys(REDIS_KEY);
		log.info("==>  keys set size : {}", keys.size());

		// concert
		Set<String> collect = keys.stream().map(m -> String.valueOf(m)).collect(Collectors.toSet());
		log.info("==>  collect set size : {}", collect.size());
	}

	/**
	 * 批量获取
	 */
	@GetMapping("/multiGet")
	public void multiGet() {
		// init
		HashMap<String, Object> hashMap = init();
		redisTemplate.opsForHash().putAll(REDIS_KEY, hashMap);

		List<Object> list = redisTemplate.opsForHash().multiGet(REDIS_KEY, Arrays.asList("name", "gender"));
		log.info("==>  list size : {}", list.size());
	}

	// -----------------------------------------------------------------------------------------------
	// EXTRA METHOD
	// -----------------------------------------------------------------------------------------------

	/**
	 * Init hash map
	 *
	 * @return the hash map
	 */
	public HashMap<String, Object> init() {
		// init
		HashMap<String, Object> hashMap = new HashMap<>(3);
		hashMap.put("name", "curry");
		hashMap.put("gender", "man");
		hashMap.put("age", 32);
		return hashMap;
	}

}