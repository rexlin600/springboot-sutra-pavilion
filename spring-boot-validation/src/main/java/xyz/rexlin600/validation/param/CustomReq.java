package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rexlin600.validation.validator.Phone;

import java.io.Serializable;

/**
 * Custom req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomReq implements Serializable {

	// -----------------------------------------------------------------------------------------------
	// 自定义校验
	// -----------------------------------------------------------------------------------------------

	/**
	 * Phone
	 */
	@Phone(message = "参数错误：手机号格式错误")
	private String phone;


}