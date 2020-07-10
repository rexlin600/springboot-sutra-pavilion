package xyz.rexlin600.validation.validator;

import org.springframework.util.StringUtils;
import xyz.rexlin600.validation.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Id card validator
 *
 * @author hekunlin
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

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
	public void initialize(IdCard constraintAnnotation) {
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
			return ValidatorUtil.isIdCard(value);
		} else {
			if (StringUtils.isEmpty(value)) {
				return true;
			} else {
				return ValidatorUtil.isIdCard(value);
			}
		}
	}

}