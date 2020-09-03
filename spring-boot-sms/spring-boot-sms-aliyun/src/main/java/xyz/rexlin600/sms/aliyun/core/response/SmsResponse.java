package xyz.rexlin600.sms.aliyun.core.response;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Sms response.
 *
 * @author hekunlin
 * @since 2020 /9/2
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SmsResponse {

	/**
	 * 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态
	 */
	private String bizId;

	/**
	 * 请求状态码
	 * <p>
	 * 返回OK代表请求成功
	 * 其他错误码详见错误码列表 https://help.aliyun.com/document_detail/101346.html?spm=a2c4g.11186623.2.14.3bf556e03nBWgO
	 */
	private String code;

	/**
	 * 状态码的描述
	 */
	private String message;

	/**
	 * 请求ID
	 */
	private String requestId;

	public static SmsResponse build(JSONObject jsonObject) {
		return SmsResponse.builder()
				.bizId(jsonObject.getStr("BizId"))
				.code(jsonObject.getStr("Code"))
				.message(jsonObject.getStr("Message"))
				.requestId(jsonObject.getStr("RequestId"))
				.build();

	}

	// -----------------------------------------------------------------------------------------------
	// EXTRA METHOD
	// -----------------------------------------------------------------------------------------------

	/**
	 * Is success boolean.
	 *
	 * @return the boolean
	 */
	public boolean isSuccess() {
		return "OK".equals(code);
	}

}