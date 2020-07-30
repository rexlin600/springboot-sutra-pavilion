package xyz.rexlin600.validation.validator;

import org.springframework.util.StringUtils;
import xyz.rexlin600.validation.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * 日期校验器
 *
 * @author hekunlin
 * @since 2020/7/29
 */
public class IsDateValidator implements ConstraintValidator<IsDate, Date> {

	/**
	 * Required
	 */
	private boolean required = false;

	/**
	 * Initialize *
	 *
	 * @param constraintAnnotation constraint annotation
	 */
	@Override
	public void initialize(IsDate constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	/**
	 * Is valid boolean
	 *
	 * @param value   value
	 * @param context context
	 * @return the boolean
	 */
	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		if (required) {
			return ValidatorUtil.isDate(value);
		} else {
			if (StringUtils.isEmpty(value)) {
				return true;
			} else {
				return ValidatorUtil.isDate(value);
			}
		}
	}

}