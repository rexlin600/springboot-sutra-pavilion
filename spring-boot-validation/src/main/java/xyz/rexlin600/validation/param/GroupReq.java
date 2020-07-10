package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rexlin600.validation.param.group.Age;
import xyz.rexlin600.validation.param.group.Classes;
import xyz.rexlin600.validation.param.group.Name;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Group req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupReq implements Serializable {

	/**
	 * Name
	 */
	@NotBlank(message = "参数错误：姓名不可为空", groups = {Name.class})
	private String name;

	/**
	 * Classes
	 */
	@NotBlank(message = "参数错误：班级不可为空", groups = {Classes.class})
	private String classes;

	/**
	 * Age
	 */
	@NotNull(message = "参数错误：年龄不可为空", groups = {Age.class})
	private Integer age;

	/**
	 * List
	 */
	@Valid
	@NotEmpty(message = "参数错误：集合不可为空")
	private List<@NotBlank(message = "参数错误：集合元素不可为空") String> list;

}