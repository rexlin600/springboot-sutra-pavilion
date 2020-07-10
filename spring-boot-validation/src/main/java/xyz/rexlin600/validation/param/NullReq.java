package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * Null req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NullReq implements Serializable {

	// -----------------------------------------------------------------------------------------------
	// 空检查
	// -----------------------------------------------------------------------------------------------

	/**
	 * Id
	 */
	@Null(message = "ID必须为空")
	private Long id;

	/**
	 * Name
	 */
	@NotBlank(message = "参数错误：名称不可为空")
	private String name;

	/**
	 * Sort
	 */
	@NotNull(message = "参数错误：序号不可为空")
	private Integer sort;

	/**
	 * List
	 */
	@Valid
	@NotEmpty(message = "参数错误：列表不可为空")
	private List<NullInnerReq> list;

}