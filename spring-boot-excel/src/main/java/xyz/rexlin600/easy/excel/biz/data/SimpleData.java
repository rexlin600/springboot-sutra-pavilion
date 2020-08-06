package xyz.rexlin600.easy.excel.biz.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hekunlin
 * @since 2020/8/6
 */
@Data
public class SimpleData implements Serializable {

	/**
	 * 用户名称
	 */
	@ExcelProperty({"姓名"})
	private String name;

}