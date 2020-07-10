package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * Other req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherReq implements Serializable {

	// -----------------------------------------------------------------------------------------------
	// 其他校验
	// -----------------------------------------------------------------------------------------------

	/**
	 * Email
	 */
	@Email(message = "参数错误：邮箱格式不正确")
	private String email;

	/**
	 * Credit card
	 */
	@CreditCardNumber(message = "参数错误：信用卡格式不合法")
	private String creditCard;

	/**
	 * URL 校验
	 */
	//@org.hibernate.validator.constraints.URL(protocol = "https", host = "www.baidu.com", port = -1)
	//private URL url;

}