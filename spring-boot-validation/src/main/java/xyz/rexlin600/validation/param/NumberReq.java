package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Number req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumberReq implements Serializable {

	// -----------------------------------------------------------------------------------------------
	// 数值检查
	// -----------------------------------------------------------------------------------------------

	/**
	 * Distance
	 */
	@Min(value = 0, message = "参数错误：距离最小为0")
	@Max(value = 1000, message = "参数错误：距离最大为1000")
	private Integer distance;

	/**
	 * School
	 */
	@Min(value = 2, message = "参数错误：学校名称字符最小为2")
	@Max(value = 100, message = "参数错误：学校名称字符最大为100")
	private String school;

	/**
	 * Weight
	 */
	@DecimalMin(value = "0.01", message = "参数错误：重量最少0.01")
	@DecimalMax(value = "999.99", message = "参数错误：重量最大999.99")
	private BigDecimal weight;

	/**
	 * Grade
	 */
	@Digits(integer = 3, fraction = 2, message = "参数错误：整数部分最大3位、小数部分最大2位")
	private Double grade;

}