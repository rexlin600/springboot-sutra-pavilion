package xyz.rexlin600.skywalking.common.apiparam;

import lombok.Data;
import xyz.rexlin600.skywalking.common.statuscode.StatusCode;
import xyz.rexlin600.skywalking.exception.BusinessException;

import java.io.Serializable;

/**
 * Response
 *
 * @param <T> parameter
 * @author hekunlin
 */
@Data
public class Response<T> implements Serializable {

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

	/**
	 * Fallback response
	 *
	 * @return the response
	 */
	public Response<T> fallback() {
		return fallback(code, msg);
	}

	/**
	 * Fallback response
	 *
	 * @param msg msg
	 * @return the response
	 */
	public Response<T> fallback(String msg) {
		return fallback(code, msg);
	}

	/**
	 * Fallback response
	 *
	 * @param code code
	 * @return the response
	 */
	public Response<T> fallback(long code) {
		return fallback(code, msg);
	}

	/**
	 * Fallback response
	 *
	 * @param code code
	 * @param msg  msg
	 * @return the response
	 */
	public Response<T> fallback(long code, String msg) {
		if (!success) {
			BusinessException ex = new BusinessException(Long.valueOf(code).intValue(), msg);
			ex.setErrorMsg(errorMsg);
			throw ex;
		}
		return this;
	}
}
