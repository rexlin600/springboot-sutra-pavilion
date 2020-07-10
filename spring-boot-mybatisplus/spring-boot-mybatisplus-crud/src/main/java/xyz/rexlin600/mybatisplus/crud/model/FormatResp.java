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
 * Format resp
 *
 * @author hekunlin
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormatResp {

	// -----------------------------------------------------------------------------------------------
	// DATE
	// -----------------------------------------------------------------------------------------------

	/**
	 * Create date
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	/**
	 * Delete date
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deleteDate;

	/**
	 * Update date
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDate;

	/**
	 * Search date
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date searchDate;

	// -----------------------------------------------------------------------------------------------
	// LocalDateTime
	// -----------------------------------------------------------------------------------------------

	/**
	 * Local update date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime localUpdateDate;

	/**
	 * Local search date
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime localSearchDate;

}