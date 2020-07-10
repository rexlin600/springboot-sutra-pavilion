package xyz.rexlin600.validation.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator util
 *
 * @author hekunlin
 */
public class ValidatorUtil {

	/**
	 * NICKNAME_CONSTRAINT_REGEXP
	 */
	public static final String NICKNAME_CONSTRAINT_REGEXP = "^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{1,20}$";
	/**
	 * USER_PASSWORD_CONSTRAINT_REGEXP
	 */
	public static final String USER_PASSWORD_CONSTRAINT_REGEXP = "^.{8,16}$";

	/**
	 * PHONE_CONSTRAINT_REGEXP
	 */
	public static final String PHONE_CONSTRAINT_REGEXP = "^(1)\\d{10}$";
	/**
	 * PHONE_BLUR_REGEX
	 */
	public static final String PHONE_BLUR_REGEX = "(\\d{3})\\d{4}(\\d{4})";

	/**
	 * ID_CARD_CONSTRAINT_REGEXP
	 */
	public static final String ID_CARD_CONSTRAINT_REGEXP = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";

	// -----------------------------------------------------------------------------------------------
	// 实用方法
	// -----------------------------------------------------------------------------------------------

	/**
	 * Is phone boolean
	 *
	 * @param value value
	 * @return the boolean
	 */
	public static boolean isPhone(String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}

		Pattern pattern = Pattern.compile(PHONE_CONSTRAINT_REGEXP);
		Matcher matcher = pattern.matcher(value);

		return matcher.matches();
	}

	/**
	 * Is id card boolean
	 *
	 * @param value value
	 * @return the boolean
	 */
	public static boolean isIdCard(String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}

		Pattern pattern = Pattern.compile(ID_CARD_CONSTRAINT_REGEXP);
		Matcher matcher = pattern.matcher(value);

		return matcher.matches();
	}

	/**
	 * Is nick name boolean
	 *
	 * @param value value
	 * @return the boolean
	 */
	public static boolean isNickName(String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}

		Pattern pattern = Pattern.compile(NICKNAME_CONSTRAINT_REGEXP);
		Matcher matcher = pattern.matcher(value);

		return matcher.matches();
	}

	/**
	 * Is password boolean
	 *
	 * @param value value
	 * @return the boolean
	 */
	public static boolean isPassword(String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}

		Pattern pattern = Pattern.compile(USER_PASSWORD_CONSTRAINT_REGEXP);
		Matcher matcher = pattern.matcher(value);

		return matcher.matches();
	}


}