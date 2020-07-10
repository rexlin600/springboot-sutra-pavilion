package xyz.rexlin600.mybatis.interceptor.annotation;

import java.lang.annotation.*;

/**
 * Inject sql
 *
 * @author hekunlin
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectSql {

	/**
	 * Flag boolean
	 *
	 * @return the boolean
	 */
	boolean flag() default false;

}