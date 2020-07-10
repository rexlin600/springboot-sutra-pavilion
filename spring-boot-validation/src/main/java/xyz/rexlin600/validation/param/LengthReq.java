package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Length req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LengthReq implements Serializable {

	// -----------------------------------------------------------------------------------------------
	// 长度检查
	// -----------------------------------------------------------------------------------------------

	/**
	 * Content
	 */
	@Length(message = "参数错误：内容字符不得超过20", min = 0, max = 20)
	private String content;

	/**
	 * Array list
	 */
	@Size(min = 1, max = 2, message = "参数错误：集合长度范围为 [1,2]")
	private List arrayList;

	/**
	 * Hobby
	 */
	@Size(min = 2, max = 20, message = "参数错误：爱好字符串范围为 [2,20]")
	private String hobby;

}