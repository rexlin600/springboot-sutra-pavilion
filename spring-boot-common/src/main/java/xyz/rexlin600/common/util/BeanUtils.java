//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package xyz.rexlin600.common.util;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.objenesis.instantiator.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bean utils
 *
 * @author hekunlin
 */
public final class BeanUtils {

	/**
	 * Bean utils
	 */
	private BeanUtils() {
	}

	/**
	 * Bean to map map
	 *
	 * @param bean bean
	 * @return the map
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		return null == bean ? null : BeanMap.create(bean);
	}

	/**
	 * Map to bean t
	 *
	 * @param <T>   parameter
	 * @param map   map
	 * @param clazz clazz
	 * @return the t
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
		T bean = ClassUtils.newInstance(clazz);
		BeanMap.create(bean).putAll(map);
		return bean;
	}

	/**
	 * Beans to maps list
	 *
	 * @param <T>   parameter
	 * @param beans beans
	 * @return the list
	 */
	public static <T> List<Map<String, Object>> beansToMaps(List<T> beans) {
		return CollectionUtils.isEmpty(beans) ? Collections.emptyList() : (List) beans.stream().map(BeanUtils::beanToMap).collect(Collectors.toList());
	}

	/**
	 * Maps to beans list
	 *
	 * @param <T>   parameter
	 * @param maps  maps
	 * @param clazz clazz
	 * @return the list
	 */
	public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, Class<T> clazz) {
		return CollectionUtils.isEmpty(maps) ? Collections.emptyList() : (List) maps.stream().map((e) -> {
			return mapToBean(e, clazz);
		}).collect(Collectors.toList());
	}

}
