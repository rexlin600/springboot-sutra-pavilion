package xyz.rexlin600.openfeign.svc.feign.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * History today response
 *
 * @author hekunlin
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryTodayResponse {

	/**
	 * Error code
	 */
	@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
	/**
	 * error_code
	 */
	private Integer errorCode;

	/**
	 * Reason
	 */
	private String reason;

	/**
	 * Result
	 */
	private List<Result> result;

	/**
	 * Result
	 */
	@ToString
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	static class Result {

		/**
		 * Day
		 */
		private Integer day;

		/**
		 * Des
		 */
		private String des;

		/**
		 * Id
		 */
		@SuppressWarnings("AlibabaAvoidStartWithDollarAndUnderLineNaming")
		private String id;

		/**
		 * Lunar
		 */
		private String lunar;

		/**
		 * Month
		 */
		private Integer month;

		/**
		 * Pic
		 */
		private String pic;

		/**
		 * Title
		 */
		private String title;

		/**
		 * Year
		 */
		private Integer year;

	}

}