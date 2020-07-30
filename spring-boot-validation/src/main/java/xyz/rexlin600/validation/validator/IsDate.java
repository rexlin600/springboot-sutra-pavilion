package xyz.rexlin600.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 日期校验器
 *
 * @author hekunlin
 * @since 2020/7/29
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsDateValidator.class})
public @interface IsDate {

	/**
	 * Required boolean
	 *
	 * @return the boolean
	 */
	boolean required() default true;

	/**
	 * Message string
	 *
	 * @return the string
	 */
	String message() default "日期格式不正确";

	/**
	 * Groups class [ ]
	 *
	 * @return the class [ ]
	 */
	Class<?>[] groups() default {};

	/**
	 * Payload class [ ]
	 *
	 * @return the class [ ]
	 */
	Class<? extends Payload>[] payload() default {};

}