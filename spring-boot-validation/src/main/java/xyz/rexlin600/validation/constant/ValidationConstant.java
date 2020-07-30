package xyz.rexlin600.validation.constant;

/**
 * 校验常量
 *
 * @author hekunlin
 * @since 2020 /7/29
 */
public class ValidationConstant {

	// -----------------------------------------------------------------------------------------------
	// 基础
	// -----------------------------------------------------------------------------------------------

	/**
	 * 汉字
	 */
	public static final String CHINESE_CHARACTER_REGEXP = "^[\\u4e00-\\u9fa5]{0,}$";

	/**
	 * 英文和汉字
	 */
	public static final String CHINESE_AND_ENGLISH_CHARACTER_REGEXP = "^[\\u4e00-\\u9fa5]{0,}$";

	/**
	 * 数字、26个字母、下划线
	 */
	public static final String COMMON_CHARACTER_REGEXP = "^\\w+$ 或 ^\\w{3,20}$";

	/**
	 * 邮箱
	 */
	public static final String EMAIL_REGEXP = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$\\w{3,20}$";

	/**
	 * 域名
	 */
	public static final String DOMAIN_REGEXP = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?";

	/**
	 * 中国电话号码
	 */
	public static final String CHINA_TEL_REGEXP = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";

	/**
	 * 邮政编码
	 */
	public static final String POSTAL_CODE_REGEXP = "[1-9]\\d{5}(?!\\d)";

	/**
	 * QQ号
	 */
	public static final String QQ_REGEXP = "[1-9][0-9]{4,}";

	/**
	 * IPV4 地址
	 */
	public static final String IPV4_REGEXP = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";

	/**
	 * 短日期 yyyy-MM-dd 已考虑平闰年
	 */
	public static final String DATE_SHORT_REGEXP = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";

	/**
	 * 简单日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_COMMON_REGEXP = "^\\d{4}-\\d{1,2}-\\d{1,2}";

	/**
	 * 简单日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_SIMPLE_REGEXP = "^([1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9])\\s(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";

	// -----------------------------------------------------------------------------------------------
	// 业务常用
	// -----------------------------------------------------------------------------------------------

	/**
	 * 昵称
	 */
	public static final String NICKNAME_REGEXP = "^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{1,20}$";

	/**
	 * 用户密码
	 */
	public static final String USER_PASSWORD_REGEXP = "^.{8,16}$";

	/**
	 * 简单手机号
	 */
	public static final String PHONE_REGEXP = "^(1)\\d{10}$";
	/**
	 * 脱敏手机号
	 */
	public static final String PHONE_BLUR_REGEX = "(\\d{3})\\d{4}(\\d{4})";

	/**
	 * 身份证
	 */
	public static final String ID_CARD_REGEXP = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";

	/**
	 * 身份证号 15、18位
	 */
	public static final String ID_CARD_COMMON_REGEXP = "^\\d{15}|\\d{18}$";

}