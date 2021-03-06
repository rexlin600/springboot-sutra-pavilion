package xyz.rexlin600.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Password
 *
 * @author hekunlin
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordValidator.class})
public @interface Password {

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
	String message() default "密码格式错误";

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