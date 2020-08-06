package xyz.rexlin600.easy.excel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 雇员类 DO
 * </p>
 *
 * @author rexlin600
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("employees")
public class EmployeesDO extends Model {

	private static final long serialVersionUID = 1L;

	@TableId(value = "emp_no", type = IdType.AUTO)
	private Integer empNo;

	private Date birthDate;

	private String firstName;

	private String lastName;

	private String gender;

	private Date hireDate;


}
