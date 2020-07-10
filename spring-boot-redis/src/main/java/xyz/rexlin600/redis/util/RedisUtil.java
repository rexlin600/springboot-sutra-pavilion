package xyz.rexlin600.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis util
 *
 * @author hekunlin
 */
@Component
public class RedisUtil {

	/**
	 * Redis template
	 */
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * Redis util
	 *
	 * @param redisTemplate redis template
	 */
	@Autowired
	public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * Expire boolean
	 *
	 * @param key  key
	 * @param time time
	 * @return the boolean
	 */
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets expire *
	 *
	 * @param key key
	 * @return the expire
	 */
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * Has key boolean
	 *
	 * @param key key
	 * @return the boolean
	 */
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Del *
	 *
	 * @param key key
	 */
	@SuppressWarnings("unchecked")
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	//============================String=============================

	/**
	 * Get object
	 *
	 * @param key key
	 * @return the object
	 */
	public Object get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	/**
	 * Set boolean
	 *
	 * @param key   key
	 * @param value value
	 * @return the boolean
	 */
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Set boolean
	 *
	 * @param key   key
	 * @param value value
	 * @param time  time
	 * @return the boolean
	 */
	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Incr long
	 *
	 * @param key   key
	 * @param delta delta
	 * @return the long
	 */
	public long incr(String key, long delta) {
		if (delta < 0) {
			throw new InvalidParameterException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * Decr long
	 *
	 * @param key   key
	 * @param delta delta
	 * @return the long
	 */
	public long decr(String key, long delta) {
		if (delta < 0) {
			throw new InvalidParameterException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	//================================Map=================================

	/**
	 * Hget object
	 *
	 * @param key  key
	 * @param item item
	 * @return the object
	 */
	public Object hget(String key, String item) {
		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * Hmget map
	 *
	 * @param key key
	 * @return the map
	 */
	public Map<Object, Object> hmget(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * Hmset boolean
	 *
	 * @param key key
	 * @param map map
	 * @return the boolean
	 */
	public boolean hmset(String key, Map<String, Object> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Hmset boolean
	 *
	 * @param key  key
	 * @param map  map
	 * @param time time
	 * @return the boolean
	 */
	public boolean hmset(String key, Map<String, Object> map, long time) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Hset boolean
	 *
	 * @param key   key
	 * @param item  item
	 * @param value value
	 * @return the boolean
	 */
	public boolean hset(String key, String item, Object value) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Hset boolean
	 *
	 * @param key   key
	 * @param item  item
	 * @param value value
	 * @param time  time
	 * @return the boolean
	 */
	public boolean hset(String key, String item, Object value, long time) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Hdel *
	 *
	 * @param key  key
	 * @param item item
	 */
	public void hdel(String key, Object... item) {
		redisTemplate.opsForHash().delete(key, item);
	}

	/**
	 * H has key boolean
	 *
	 * @param key  key
	 * @param item item
	 * @return the boolean
	 */
	public boolean hHasKey(String key, String item) {
		return redisTemplate.opsForHash().hasKey(key, item);
	}

	/**
	 * Hincr double
	 *
	 * @param key  key
	 * @param item item
	 * @param by   by
	 * @return the double
	 */
	public double hincr(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, by);
	}

	/**
	 * Hdecr double
	 *
	 * @param key  key
	 * @param item item
	 * @param by   by
	 * @return the double
	 */
	public double hdecr(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, -by);
	}

	//============================set=============================

	/**
	 * S get set
	 *
	 * @param key key
	 * @return the set
	 */
	public Set<Object> sGet(String key) {
		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * S has key boolean
	 *
	 * @param key   key
	 * @param value value
	 * @return the boolean
	 */
	public boolean sHasKey(String key, Object value) {
		try {
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * S set long
	 *
	 * @param key    key
	 * @param values values
	 * @return the long
	 */
	public long sSet(String key, Object... values) {
		try {
			return redisTemplate.opsForSet().add(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * S set and time long
	 *
	 * @param key    key
	 * @param time   time
	 * @param values values
	 * @return the long
	 */
	public long sSetAndTime(String key, long time, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().add(key, values);
			if (time > 0) {
				expire(key, time);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * S get set size long
	 *
	 * @param key key
	 * @return the long
	 */
	public long sGetSetSize(String key) {
		try {
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Sets remove *
	 *
	 * @param key    key
	 * @param values values
	 * @return the remove
	 */
	public long setRemove(String key, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().remove(key, values);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//===============================list=================================

	/**
	 * L get list
	 *
	 * @param key   key
	 * @param start start
	 * @param end   end
	 * @return the list
	 */
	public List<Object> lGet(String key, long start, long end) {
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * L get list size long
	 *
	 * @param key key
	 * @return the long
	 */
	public long lGetListSize(String key) {
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * L get index object
	 *
	 * @param key   key
	 * @param index index
	 * @return the object
	 */
	public Object lGetIndex(String key, long index) {
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * L set boolean
	 *
	 * @param key   key
	 * @param value value
	 * @return the boolean
	 */
	public boolean lSet(String key, Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * L set boolean
	 *
	 * @param key   key
	 * @param value value
	 * @param time  time
	 * @return the boolean
	 */
	public boolean lSet(String key, Object value, long time) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * L set boolean
	 *
	 * @param key   key
	 * @param value value
	 * @return the boolean
	 */
	public boolean lSet(String key, List<Object> value) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * L set boolean
	 *
	 * @param key   key
	 * @param value value
	 * @param time  time
	 * @return the boolean
	 */
	public boolean lSet(String key, List<Object> value, long time) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * L update index boolean
	 *
	 * @param key   key
	 * @param index index
	 * @param value value
	 * @return the boolean
	 */
	public boolean lUpdateIndex(String key, long index, Object value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * L remove long
	 *
	 * @param key   key
	 * @param count count
	 * @param value value
	 * @return the long
	 */
	public long lRemove(String key, long count, Object value) {
		try {
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}