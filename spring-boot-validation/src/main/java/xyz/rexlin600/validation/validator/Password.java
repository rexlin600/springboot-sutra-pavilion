package xyz.rexlin600.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 密码校验器
 *
 * @author: hekunlin
 * @since: 2020/6/21
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordValidator.class})
public @interface Password {

    boolean required() default true;

    String message() default "密码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}