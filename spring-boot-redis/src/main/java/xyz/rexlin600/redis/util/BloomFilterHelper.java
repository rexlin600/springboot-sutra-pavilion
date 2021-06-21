package xyz.rexlin600.redis.util;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;

/**
 * 布隆过滤器辅助类
 * <p>
 * init func {@link xyz.rexlin600.redis.config.RedisConfig#initBloomFilterHelper}
 *
 * @param <T> the type parameter
 */
public class BloomFilterHelper<T> {

	// Hash函数数量
	private int numHashFunctions;

	// 位大小
	private int bitSize;

	// funnel
	private Funnel<T> funnel;

	/**
	 * Instantiates a new Bloom filter helper.
	 *
	 * @param funnel             the funnel
	 * @param expectedInsertions the expected insertions
	 * @param fpp                the fpp
	 */
	public BloomFilterHelper(Funnel<T> funnel, int expectedInsertions, double fpp) {
		Preconditions.checkArgument(funnel != null, "funnel不能为空");
		this.funnel = funnel;
		// 计算bit数组长度
		bitSize = optimalNumOfBits(expectedInsertions, fpp);
		// 计算hash方法执行次数
		numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
	}

	/**
	 * 计算给定数据的偏移量数组（将指定在 bitmap 的 offset 的值为1）
	 *
	 * @param value the value
	 * @return the int [ ]
	 */
	public int[] murmurHashOffset(T value) {
		int[] offset = new int[numHashFunctions];

		long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
		int hash1 = (int) hash64;
		int hash2 = (int) (hash64 >>> 32);
		for (int i = 1; i <= numHashFunctions; i++) {
			int nextHash = hash1 + i * hash2;
			if (nextHash < 0) {
				nextHash = ~nextHash;
			}
			offset[i - 1] = nextHash % bitSize;
		}

		return offset;
	}

	/**
	 * 计算bit数组长度
	 */
	private int optimalNumOfBits(long n, double p) {
		if (p == 0) {
			// 设定最小期望长度
			p = Double.MIN_VALUE;
		}
		return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
	}

	/**
	 * 计算hash方法执行次数
	 */
	private int optimalNumOfHashFunctions(long n, long m) {
		return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
	}

}
