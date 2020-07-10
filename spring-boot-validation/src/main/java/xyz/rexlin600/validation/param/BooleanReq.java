package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

/**
 * Boolean req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooleanReq implements Serializable {

	// -----------------------------------------------------------------------------------------------
	// Boolean 检查
	// -----------------------------------------------------------------------------------------------

	/**
	 * Rich
	 */
	@AssertTrue(message = "参数错误：您必须富有")
	private Boolean rich;

	/**
	 * Hand some
	 */
	@AssertFalse(message = "参数错误：您并不英俊")
	private Boolean handSome;
}