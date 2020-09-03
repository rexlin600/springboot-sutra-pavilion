package xyz.rexlin600.sms.aliyun.core.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Verify code request.
 *
 * @author hekunlin
 * @since 2020 /9/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCodeRequest implements Serializable {

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 短信模板Code
	 */
	private String templateCode;

	/**
	 * 验证码
	 */
	private String code;

}