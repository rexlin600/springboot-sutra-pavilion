package xyz.rexlin600.redis.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * Redis SortedSet接口
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/redis/zset")
public class RedisSortedSetRest {

	/**
	 * ZSET_KEY
	 */
	private final static String ZSET_KEY = "ZSET_KEY";

	/**
	 * Redis template
	 */
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Redis sorted set rest
	 *
	 * @param redisTemplate redis template
	 */
	@Autowired
	public RedisSortedSetRest(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 新增
	 */
	@PostMapping
	public void add() {
		ZSetOperations<String, Object> opsForSet = redisTemplate.opsForZSet();

		// add
		opsForSet.add(ZSET_KEY, "1", 1D);

		// multi add
		ZSetOperations.TypedTuple<Object> typedTuple1 = new DefaultTypedTuple<Object>("2", 2D);
		ZSetOperations.TypedTuple<Object> typedTuple2 = new DefaultTypedTuple<Object>("3", 3D);
		ZSetOperations.TypedTuple<Object> typedTuple3 = new DefaultTypedTuple<Object>("5", 5D);
		ZSetOperations.TypedTuple<Object> typedTuple4 = new DefaultTypedTuple<Object>("4", 4D);
		Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = new HashSet<ZSetOperations.TypedTuple<Object>>();
		typedTupleSet.add(typedTuple1);
		typedTupleSet.add(typedTuple2);
		typedTupleSet.add(typedTuple3);
		typedTupleSet.add(typedTuple4);
		opsForSet.add(ZSET_KEY, typedTupleSet);
	}

	/**
	 * 遍历Range
	 */
	@GetMapping("/range")
	public void range() {
		ZSetOperations<String, Object> opsForSet = redisTemplate.opsForZSet();

		Set<Object> set1 = opsForSet.range(ZSET_KEY, 0, -1);
		log.info("==>  set1 value is : {}", set1.toString());

		// range
		// 用于获取满足非score的排序取值 这个排序只有在有相同分数的情况下才能使用，如果有不同的分数则返回值不确定
		RedisZSetCommands.Range range = new RedisZSetCommands.Range();
		range.lt("A");
		Set<Object> set2 = opsForSet.rangeByLex(ZSET_KEY, range);
		log.info("==>  set2 value is : {}", set2.toString());

		// limit
		RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
		limit.count(2);
		// 起始下标为0
		limit.offset(1);
		Set<Object> set3 = opsForSet.rangeByLex(ZSET_KEY, range, limit);
		log.info("==>  set3 value is : {}", set3.toString());
	}


}