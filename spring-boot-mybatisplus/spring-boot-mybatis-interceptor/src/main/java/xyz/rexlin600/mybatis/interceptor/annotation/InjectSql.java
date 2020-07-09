package xyz.rexlin600.mybatis.interceptor.annotation;

import java.lang.annotation.*;

/**
 * 注入SQL注解
 *
 * @author: hekunlin
 * @since: 2020/5/9
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectSql {

    /**
     * 是否进行SQL注入，默认false
     *
     * @return
     */
    boolean flag() default false;

}