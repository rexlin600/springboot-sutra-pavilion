package xyz.rexlin600.common.util.sensitive;

import cn.hutool.core.util.StrUtil;

/**
 * Sensitive utils
 *
 * @author hekunlin
 */
public class SensitiveUtils {

	/**
	 * Des value string
	 *
	 * @param origin          origin
	 * @param prefixNoMaskLen prefix no mask len
	 * @param suffixNoMaskLen suffix no mask len
	 * @param maskStr         mask str
	 * @return the string
	 */
	public static String desValue(String origin, int prefixNoMaskLen, int suffixNoMaskLen, String maskStr) {
		if (origin == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0, n = origin.length(); i < n; i++) {
			if (i < prefixNoMaskLen) {
				sb.append(origin.charAt(i));
				continue;
			}
			if (i > (n - suffixNoMaskLen - 1)) {
				sb.append(origin.charAt(i));
				continue;
			}
			sb.append(maskStr);
		}
		return sb.toString();
	}

	/**
	 * Chinese name string
	 *
	 * @param fullName full name
	 * @return the string
	 */
	public static String chineseName(String fullName) {
		if (fullName == null) {
			return null;
		}
		return desValue(fullName, 0, 1, "*");
	}

	/**
	 * Id card num string
	 *
	 * @param id id
	 * @return the string
	 */
	public static String idCardNum(String id) {
		return desValue(id, 6, 4, "*");
	}

	/**
	 * Fixed phone string
	 *
	 * @param num num
	 * @return the string
	 */
	public static String fixedPhone(String num) {
		return desValue(num, 0, 4, "*");
	}

	/**
	 * Mobile phone string
	 *
	 * @param num num
	 * @return the string
	 */
	public static String mobilePhone(String num) {
		return desValue(num, 3, 4, "*");
	}

	/**
	 * Address string
	 *
	 * @param address address
	 * @return the string
	 */
	public static String address(String address) {
		return desValue(address, 6, 0, "*");
	}

	/**
	 * Email string
	 *
	 * @param email email
	 * @return the string
	 */
	public static String email(String email) {
		if (email == null) {
			return null;
		}
		int index = StrUtil.indexOf(email, '@');
		if (index <= 1) {
			return email;
		}
		String preEmail = desValue(email.substring(0, index), 1, 0, "*");
		return preEmail + email.substring(index);

	}

	/**
	 * Bank card string
	 *
	 * @param cardNum card num
	 * @return the string
	 */
	public static String bankCard(String cardNum) {
		return desValue(cardNum, 6, 4, "*");
	}

	/**
	 * Password string
	 *
	 * @param password password
	 * @return the string
	 */
	public static String password(String password) {
		if (password == null) {
			return null;
		}
		return "******";
	}

	/**
	 * Key string
	 *
	 * @param key key
	 * @return the string
	 */
	public static String key(String key) {
		if (key == null) {
			return null;
		}
		int viewLength = 6;
		StringBuilder tmpKey = new StringBuilder(desValue(key, 0, 3, "*"));
		if (tmpKey.length() > viewLength) {
			return tmpKey.substring(tmpKey.length() - viewLength);
		} else if (tmpKey.length() < viewLength) {
			int buffLength = viewLength - tmpKey.length();
			for (int i = 0; i < buffLength; i++) {
				tmpKey.insert(0, "*");
			}
			return tmpKey.toString();
		} else {
			return tmpKey.toString();
		}
	}

}
