package xyz.rexlin600.common.util.repeat;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防重复提交注解类
 *
 * @author hekunlin
 * @since 2020 /7/30
 */
@Qualifier(value = "noRepeatSubmit")
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoRepeatCommit {

	/**
	 * Required boolean.
	 *
	 * @return the boolean
	 */
	boolean required() default true;

	/**
	 * Message string.
	 *
	 * @return the string
	 */
	String message() default "不允许重复提交";

	/**
	 * Lock time long. 单位：ms
	 *
	 * @return the long
	 */
	long lockTime() default 10;

}