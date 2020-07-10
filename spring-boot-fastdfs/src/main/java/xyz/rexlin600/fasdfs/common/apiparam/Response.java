package xyz.rexlin600.fasdfs.common.apiparam;

import lombok.Data;
import xyz.rexlin600.fasdfs.common.statuscode.StatusCode;

/**
 * Response
 *
 * @param <T> parameter
 * @author hekunlin
 */
@Data
public class Response<T> {

	/**
	 * Success
	 */
	private boolean success = true;

	/**
	 * Code
	 */
	private long code = StatusCode.SUCCESS.getCode();

	/**
	 * Msg
	 */
	private String msg = StatusCode.SUCCESS.getMsg();

	/**
	 * Error msg
	 */
	private String errorMsg = StatusCode.SUCCESS.getMsg();

	/**
	 * Data
	 */
	private T data;

}
