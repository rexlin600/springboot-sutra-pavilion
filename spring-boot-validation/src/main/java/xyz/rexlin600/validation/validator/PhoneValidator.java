package xyz.rexlin600.validation.validator;

import org.springframework.util.StringUtils;
import xyz.rexlin600.validation.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Phone validator
 *
 * @author hekunlin
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

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
	public void initialize(Phone constraintAnnotation) {
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
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (required) {
			return ValidatorUtil.isPhone(value);
		} else {
			if (StringUtils.isEmpty(value)) {
				return true;
			} else {
				return ValidatorUtil.isPhone(value);
			}
		}
	}

}