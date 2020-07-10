package xyz.rexlin600.redis.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * Fast json redis serializer
 *
 * @param <T> parameter
 * @author hekunlin
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

	/**
	 * DEFAULT_CHARSET
	 */
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	/**
	 * Clazz
	 */
	private Class<T> clazz;

	/**
	 * Fast json redis serializer
	 *
	 * @param clazz clazz
	 */
	public FastJsonRedisSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	/**
	 * Serialize byte [ ]
	 *
	 * @param t t
	 * @return the byte [ ]
	 * @throws SerializationException serialization exception
	 */
	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (t == null) {
			return new byte[0];
		}
		return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
	}

	/**
	 * Deserialize t
	 *
	 * @param bytes bytes
	 * @return the t
	 * @throws SerializationException serialization exception
	 */
	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		String str = new String(bytes, DEFAULT_CHARSET);
		return JSON.parseObject(str, clazz);
	}

	/**
	 * Sets object mapper *
	 *
	 * @param objectMapper object mapper
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "'objectMapper' must not be null");
	}

}