package xyz.rexlin600.easy.excel.restful.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
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
@TableName("employees")
public class EmployeesDO implements Serializable {

	@TableId(value = "emp_no", type = IdType.AUTO)
	private Integer empNo;

	private Date birthDate;

	private String firstName;

	private String lastName;

	private String gender;

	private Date hireDate;


}
