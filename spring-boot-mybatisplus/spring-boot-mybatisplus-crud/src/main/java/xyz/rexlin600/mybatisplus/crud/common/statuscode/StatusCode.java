package xyz.rexlin600.mybatisplus.crud.common.statuscode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Status code
 *
 * @author hekunlin
 */
@Getter
@AllArgsConstructor
public enum StatusCode implements IStatusCode {

	/**
	 * Success status code
	 */
	SUCCESS(1000, "操作成功", "操作成功"),
	/**
	 * Business error status code
	 */
	BUSINESS_ERROR(1006, "业务错误", "业务错误");


	/**
	 * Code
	 */
	private long code;

	/**
	 * Msg
	 */
	private String msg;

	/**
	 * Error msg
	 */
	private String errorMsg;
}
