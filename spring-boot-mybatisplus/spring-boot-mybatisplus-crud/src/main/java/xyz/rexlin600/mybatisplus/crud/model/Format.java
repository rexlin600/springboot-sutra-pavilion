package xyz.rexlin600.mybatisplus.crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Format
 *
 * @author hekunlin
 */
@SuppressWarnings({"ALL", "AlibabaRemoveCommentedCode"})
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Format {

	// -----------------------------------------------------------------------------------------------
	// Date
	// 下面展示 req -> resp Date 类型数据使用下面 DateTimeFormat 与 JsonFormat 的互转
	// -----------------------------------------------------------------------------------------------

	/**
	 * Create date
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	/**
	 * Delete date
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deleteDate;

	/**
	 * Update date
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDate;

	/**
	 * Search date
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date searchDate;

	// -----------------------------------------------------------------------------------------------
	// LocalDateTime
	//
	// 注意点：
	//
	// @DateTimeFormat to @DateTimeFormat：LocalDateTime 通过 @DateTimeFormat 接收参数会报错
	//
	// @DateTimeFormat to @JsonFormat：LocalDateTime 通过 @DateTimeFormat 接收参数会报错
	// -----------------------------------------------------------------------------------------------

	/**
	 * Local update date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime localUpdateDate;

	/**
	 * Local search date
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime localSearchDate;

}