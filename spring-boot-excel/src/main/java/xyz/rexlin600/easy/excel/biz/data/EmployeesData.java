package xyz.rexlin600.easy.excel.biz.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
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
@HeadRowHeight(value = 30)
@ColumnWidth(value = 10)
@Data
public class EmployeesData implements Serializable {

	@ColumnWidth(value = 12)
	@ExcelProperty(value = "雇员编号", index = 0)
	private Integer empNo;

	@ColumnWidth(value = 22)
	@ExcelProperty(value = "雇员生日", index = 1)
	private Date birthDate;

	@ColumnWidth(value = 12)
	@ExcelProperty(value = "雇员姓氏", index = 2)
	private String firstName;

	@ColumnWidth(value = 12)
	@ExcelProperty(value = "雇员名称", index = 3)
	private String lastName;

	@ColumnWidth(value = 12)
	@ExcelProperty(value = "雇员性别", index = 4)
	private String gender;

	@ColumnWidth(value = 22)
	@ExcelProperty(value = "雇用日期", index = 5)
	private Date hireDate;


}
