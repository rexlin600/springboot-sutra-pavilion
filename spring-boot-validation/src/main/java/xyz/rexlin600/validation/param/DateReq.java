package xyz.rexlin600.validation.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rexlin600.validation.validator.IsDate;

import java.io.Serializable;
import java.util.Date;

/**
 * 日期请求类
 *
 * @author: hekunlin
 * @since: 2020/7/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateReq implements Serializable {

	/**
	 * 日期
	 */
	@IsDate(message = "日期格式不正确，允许的格式为 yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date reportTime;

}