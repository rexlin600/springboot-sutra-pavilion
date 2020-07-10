package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Range req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RangeReq implements Serializable {

	// -----------------------------------------------------------------------------------------------
	// Range
	// -----------------------------------------------------------------------------------------------

	/**
	 * Spend
	 */
	@Range(min = 1, max = 20, message = "参数错误：花费最多20")
	private BigDecimal spend;

	/**
	 * Address
	 */
	@Range(min = 5, max = 10, message = "参数错误：地址长度范围[5,10]")
	private String address;


}