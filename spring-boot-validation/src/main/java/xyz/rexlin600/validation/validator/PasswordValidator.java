package xyz.rexlin600.validation.validator;

import org.springframework.util.StringUtils;
import xyz.rexlin600.validation.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 验证手机号 验证器
 *
 * @author: hekunlin
 * @since: 2020/6/1
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private boolean required = false;

    @Override
    public void initialize(Password constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return ValidatorUtil.isPassword(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isPassword(value);
            }
        }
    }

}