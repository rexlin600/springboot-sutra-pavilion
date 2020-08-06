package xyz.rexlin600.easy.excel.biz.data;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class EmployeesData implements Serializable {

	@ExcelProperty(value = "雇员编号", index = 0)
	private Integer empNo;

	@ExcelProperty(value = "雇员生日", index = 1)
	private Date birthDate;

	@ExcelProperty(value = "雇员姓氏", index = 2)
	private String firstName;

	@ExcelProperty(value = "雇员名称", index = 3)
	private String lastName;

	@ExcelProperty(value = "雇员性别", index = 4)
	private String gender;

	@ExcelProperty(value = "雇用日期", index = 5)
	private Date hireDate;


}
