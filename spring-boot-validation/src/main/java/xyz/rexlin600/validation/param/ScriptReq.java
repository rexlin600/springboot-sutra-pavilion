package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ScriptAssert;

import java.io.Serializable;

/**
 * Script req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ScriptAssert(lang = "JavaScript", script = "com.rexlin600.validation.Validate.checkParams(_this.name,_this.age,_this.classes)", message = "参数错误：复杂校验失败")
public class ScriptReq implements Serializable {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Age
	 */
	private int age;

	/**
	 * Classes
	 */
	private String classes;

}