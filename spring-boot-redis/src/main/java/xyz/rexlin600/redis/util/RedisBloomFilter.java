package xyz.rexlin600.redis.util;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 基于Redis+Guava的布隆过滤器
 *
 * @author rexlin600
 */
@Service
public class RedisBloomFilter {

	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Instantiates a new Redis bloom filter.
	 *
	 * @param redisTemplate the redis template
	 */
	@Autowired
	public RedisBloomFilter(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 根据给定的布隆过滤器添加值
	 *
	 * @param <T>               the type parameter
	 * @param bloomFilterHelper the bloom filter helper
	 * @param key               the key
	 * @param value             the value
	 */
	public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
		Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");

		// 计算 value 在 bitmap 中的 offset
		int[] offset = bloomFilterHelper.murmurHashOffset(value);

		// 给 bitmap 中指定的 offset 修改为1
		for (int i : offset) {
			System.out.println("key : " + key + " " + "value : " + i);
			redisTemplate.opsForValue().setBit(key, i, true);
		}
	}

	/**
	 * 根据给定的布隆过滤器判断值是否存在
	 *
	 * @param <T>               the type parameter
	 * @param bloomFilterHelper the bloom filter helper
	 * @param key               the key
	 * @param value             the value
	 * @return the boolean
	 */
	public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
		Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");

		// 需要校验的 offset 位置
		int[] offset = bloomFilterHelper.murmurHashOffset(value);

		// 根据布隆过滤器原理，只有当所有的 offset 位置的为1时才表示这个数据在 bitmap 中存在
		for (int i : offset) {
			System.out.println("key : " + key + " " + "value : " + i);
			if (!redisTemplate.opsForValue().getBit(key, i)) {
				return false;
			}
		}

		return true;
	}

}
