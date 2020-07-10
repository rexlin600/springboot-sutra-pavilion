package xyz.rexlin600.aop.aspect.annotation.annotation;

import java.lang.annotation.*;

/**
 * Sys aop log
 *
 * @author hekunlin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysAopLog {

	/**
	 * Value string
	 *
	 * @return the string
	 */
	String value();

}