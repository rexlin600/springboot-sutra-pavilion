//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package xyz.rexlin600.common.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.objenesis.instantiator.util.ClassUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author hekunlin
 */
public final class BeanUtils {

    private BeanUtils() {
    }

    /**
     * bean to map
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean) {
        return null == bean ? null : BeanMap.create(bean);
    }

    /**
     * map to bean
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T bean = ClassUtils.newInstance(clazz);
        BeanMap.create(bean).putAll(map);
        return bean;
    }

    /**
     * beans to maps
     *
     * @param beans
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> beans) {
        return CollectionUtils.isEmpty(beans) ? Collections.emptyList() : (List) beans.stream().map(BeanUtils::beanToMap).collect(Collectors.toList());
    }

    /**
     * maps to beans
     *
     * @param maps
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, Class<T> clazz) {
        return CollectionUtils.isEmpty(maps) ? Collections.emptyList() : (List) maps.stream().map((e) -> {
            return mapToBean(e, clazz);
        }).collect(Collectors.toList());
    }

}
