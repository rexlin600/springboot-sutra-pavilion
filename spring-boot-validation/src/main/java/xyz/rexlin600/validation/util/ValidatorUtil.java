package xyz.rexlin600.validation.util;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.util.StringUtils;
import xyz.rexlin600.validation.constant.ValidationConstant;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator util
 *
 * @author hekunlin
 */
public class ValidatorUtil {

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

		Pattern pattern = Pattern.compile(ValidationConstant.PHONE_REGEXP);
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

		Pattern pattern = Pattern.compile(ValidationConstant.ID_CARD_REGEXP);
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

		Pattern pattern = Pattern.compile(ValidationConstant.NICKNAME_REGEXP);
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

		Pattern pattern = Pattern.compile(ValidationConstant.USER_PASSWORD_REGEXP);
		Matcher matcher = pattern.matcher(value);

		return matcher.matches();
	}

	/**
	 * Is date boolean
	 *
	 * @param value value
	 * @return the boolean
	 */
	public static boolean isDate(Date value) {
		if (ObjectUtil.isEmpty(value)) {
			return false;
		}

		String val;
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
			val = dateFormat.format(value.toInstant());
		} catch (Exception ex) {
			return false;
		}

		Pattern pattern = Pattern.compile(ValidationConstant.DATE_SIMPLE_REGEXP);
		Matcher matcher = pattern.matcher(val);

		return matcher.matches();
	}


}