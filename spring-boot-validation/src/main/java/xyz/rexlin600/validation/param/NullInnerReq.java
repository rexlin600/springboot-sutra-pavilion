package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Null inner req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NullInnerReq implements Serializable {

	/**
	 * Origin
	 */
	@NotBlank(message = "参数错误：产地不可为空")
	private String origin;

	/**
	 * Region id
	 */
	@NotNull(message = "参数错误：地区ID不可为空")
	private Long regionId;

}