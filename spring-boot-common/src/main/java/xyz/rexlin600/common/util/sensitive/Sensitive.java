package xyz.rexlin600.common.util.sensitive;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sensitive
 *
 * @author hekunlin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface Sensitive {

	/**
	 * Type sensitive type enum
	 *
	 * @return the sensitive type enum
	 */
	SensitiveTypeEnum type() default SensitiveTypeEnum.CUSTOMER;

	/**
	 * Prefix no mask len int
	 *
	 * @return the int
	 */
	int prefixNoMaskLen() default 0;

	/**
	 * Suffix no mask len int
	 *
	 * @return the int
	 */
	int suffixNoMaskLen() default 0;

	/**
	 * Mask str string
	 *
	 * @return the string
	 */
	String maskStr() default "*";

}
