package xyz.rexlin600.aop.aspect.annotation.annotation;

import java.lang.annotation.*;

/**
 * SysLog interface
 *
 * @author: rexlin600
 * @since: 2020-02-16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysAopLog {

    /**
     * 描述
     *
     * @return {String}
     */
    String value();

}