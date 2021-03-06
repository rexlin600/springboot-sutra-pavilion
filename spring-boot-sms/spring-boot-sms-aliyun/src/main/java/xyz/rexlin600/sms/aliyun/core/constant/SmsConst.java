package xyz.rexlin600.sms.aliyun.core.constant;

import org.springframework.http.HttpStatus;

/**
 * The type Sms constant.
 *
 * @author hekunlin
 * @since 2020 /9/2
 */
public class SmsConst {

	/**
	 * 区域ID
	 */
	public static final String REGION_PARAM = "RegionId";

	/**
	 * API的命名，固定值，如发送短信API的值为：SendSms
	 */
	public static final String SEND_SMS = "SendSms";

	/**
	 * API的版本，固定值，如短信API的值为：2017-05-25
	 */
	public static final String VERSION = "2017-05-25";

	// -----------------------------------------------------------------------------------------------
	// 短息相关常量
	// -----------------------------------------------------------------------------------------------

	/**
	 * The constant ERROR_CODE.
	 */
	public static final String ERROR_CODE = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());

	/**
	 * The constant CODE.
	 */
	public static final String CODE = "code";

	/**
	 * 验证码
	 */
	public static final String VERIFY_CODE_KEY = "sms:ali:code";

	/**
	 * 每日总阈值：针对某个具体的短信Code模板
	 */
	public static final String DAILY_THRESHOLD_KEY = "sms:ali:daily";
	/**
	 * 模板Code总阈值：针对所有的Code模板
	 */
	public static final String TEMPLATE_THRESHOLD_KEY = "sms:ali:template";

}