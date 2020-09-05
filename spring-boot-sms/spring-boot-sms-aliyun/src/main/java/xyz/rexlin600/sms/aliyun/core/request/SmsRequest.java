package xyz.rexlin600.sms.aliyun.core.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

/**
 * 发送短信请求类
 *
 * @author hekunlin
 * @since 2020 /9/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsRequest implements Serializable {

	/**
	 * 手机号
	 */
	@NotBlank(message = "参数错误：手机号不可为空")
	private String phone;

	/**
	 * 短信签名
	 */
	@NotBlank(message = "参数错误：短信签名不可为空")
	private String signName;

	/**
	 * 是否是验证码短信
	 */
	private Boolean isVerifyCode = false;

	/**
	 * 短信模板 Code 请在阿里云控制台模板管理页面模板CODE一列查看
	 */
	@NotBlank(message = "参数错误：短信模板不可为空")
	private String templateCode;

	/**
	 * 模板参数 短信模板变量对应的实际值，JSON格式，例如发送验证码：{"code":"1111"}
	 * 如果是模板短信也是在这个模板参数中添加相应的 key-value 键值对数据即可
	 */
	private Map<String, String> templateParam;

}