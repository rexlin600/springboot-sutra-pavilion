package xyz.rexlin600.validation.validator;

import org.springframework.util.StringUtils;
import xyz.rexlin600.validation.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 验证身份证号 验证器
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
public class NickNameValidator implements ConstraintValidator<NickName, String> {

    private boolean required = false;

    @Override
    public void initialize(NickName constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return ValidatorUtil.isNickName(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isNickName(value);
            }
        }
    }

}