package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Api error code
 *
 * @author hekunlin
 */
@NoArgsConstructor
@Getter
public enum ApiErrorCode {

	/**
	 * Failed api error code
	 */
	FAILED(-1L, "操作失败"),
	/**
	 * Success api error code
	 */
	SUCCESS(0L, "执行成功");

	/**
	 * Code
	 */
	private long code;

	/**
	 * Msg
	 */
	private String msg;

	/**
	 * Api error code
	 *
	 * @param code code
	 * @param msg  msg
	 */
	ApiErrorCode(long code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
