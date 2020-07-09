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
 * Bean 工具类
 *
 * @author hekunlin
 */
public final class BeanUtils {

    private BeanUtils() {
    }

    /**
     * bean to map
     *
     * @param bean bean
     * @return {@link Map}
     */
    public static Map<String, Object> beanToMap(Object bean) {
        return null == bean ? null : BeanMap.create(bean);
    }

    /**
     * map to bean
     *
     * @param map   {@link Map}
     * @param clazz Class类
     * @param <T>   泛型对象
     * @return 泛型对象
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T bean = ClassUtils.newInstance(clazz);
        BeanMap.create(bean).putAll(map);
        return bean;
    }

    /**
     * beans to maps
     *
     * @param beans bean
     * @param <T>   泛型对象
     * @return 泛型对象
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> beans) {
        return CollectionUtils.isEmpty(beans) ? Collections.emptyList() : (List) beans.stream().map(BeanUtils::beanToMap).collect(Collectors.toList());
    }

    /**
     * maps to beans
     *
     * @param maps  {@link Map}
     * @param clazz Class类
     * @param <T>   泛型对象
     * @return 泛型对象
     */
    public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, Class<T> clazz) {
        return CollectionUtils.isEmpty(maps) ? Collections.emptyList() : (List) maps.stream().map((e) -> {
            return mapToBean(e, clazz);
        }).collect(Collectors.toList());
    }

}
