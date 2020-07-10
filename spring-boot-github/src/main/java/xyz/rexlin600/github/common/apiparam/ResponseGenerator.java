package xyz.rexlin600.github.common.apiparam;


import xyz.rexlin600.github.common.statuscode.IStatusCode;
import xyz.rexlin600.github.common.statuscode.StatusCode;

/**
 * Response generator
 *
 * @author hekunlin
 */
@SuppressWarnings("Duplicates")
public class ResponseGenerator {

	/**
	 * Success response
	 *
	 * @param <T> parameter
	 * @return the response
	 */
	public static <T> Response<T> success() {
		return new Response<>();
	}

	/**
	 * Success response
	 *
	 * @param <T>  parameter
	 * @param msg  msg
	 * @param data data
	 * @return the response
	 */
	public static <T> Response<T> success(String msg, T data) {
		Response<T> response = new Response<>();
		response.setMsg(msg);
		response.setData(data);
		return response;
	}

	/**
	 * Success response
	 *
	 * @param <T>  parameter
	 * @param data data
	 * @return the response
	 */
	public static <T> Response<T> success(T data) {
		Response<T> response = new Response<>();
		response.setData(data);
		return response;
	}

	/**
	 * Success 4 msg response
	 *
	 * @param <T> parameter
	 * @param msg msg
	 * @return the response
	 */
	public static <T> Response<T> success4Msg(String msg) {
		Response<T> response = new Response<>();
		response.setMsg(msg);
		return response;
	}

	/**
	 * Fail response
	 *
	 * @param <T> parameter
	 * @param msg msg
	 * @return the response
	 */
	public static <T> Response<T> fail(String msg) {
		Response<T> response = new Response<>();
		response.setSuccess(false);
		response.setCode(StatusCode.BUSINESS_ERROR.getCode());
		response.setMsg(msg);
		response.setErrorMsg(msg);
		return response;
	}

	/**
	 * Fail response
	 *
	 * @param <T>        parameter
	 * @param statusCode status code
	 * @return the response
	 */
	public static <T> Response<T> fail(IStatusCode statusCode) {
		Response<T> response = new Response<>();
		response.setSuccess(false);
		response.setCode(statusCode.getCode());
		response.setMsg(statusCode.getMsg());
		response.setErrorMsg(statusCode.getMsg());
		return response;
	}

	/**
	 * Fail response
	 *
	 * @param <T>  parameter
	 * @param code code
	 * @param msg  msg
	 * @return the response
	 */
	public static <T> Response<T> fail(long code, String msg) {
		Response<T> response = new Response<>();
		response.setSuccess(false);
		response.setCode(code);
		response.setMsg(msg);
		response.setErrorMsg(msg);
		return response;
	}

	/**
	 * Fail response
	 *
	 * @param <T>  parameter
	 * @param code code
	 * @param msg  msg
	 * @param data data
	 * @return the response
	 */
	public static <T> Response<T> fail(long code, String msg, T data) {
		Response<T> response = new Response<>();
		response.setSuccess(false);
		response.setCode(code);
		response.setMsg(msg);
		response.setErrorMsg(msg);
		response.setData(data);
		return response;
	}

	/**
	 * Fail response
	 *
	 * @param <T>      parameter
	 * @param msg      msg
	 * @param errorMsg error msg
	 * @return the response
	 */
	public static <T> Response<T> fail(String msg, String errorMsg) {
		Response<T> response = new Response<>();
		response.setSuccess(false);
		response.setCode(StatusCode.BUSINESS_ERROR.getCode());
		response.setMsg(msg);
		response.setErrorMsg(errorMsg);
		return response;
	}

	/**
	 * Fail response
	 *
	 * @param <T>        parameter
	 * @param statusCode status code
	 * @param errorMsg   error msg
	 * @return the response
	 */
	public static <T> Response<T> fail(IStatusCode statusCode, String errorMsg) {
		Response<T> response = new Response<>();
		response.setSuccess(false);
		response.setCode(statusCode.getCode());
		response.setMsg(statusCode.getMsg());
		response.setErrorMsg(errorMsg);
		return response;
	}

	/**
	 * Fail response
	 *
	 * @param <T>      parameter
	 * @param code     code
	 * @param msg      msg
	 * @param errorMsg error msg
	 * @return the response
	 */
	public static <T> Response<T> fail(long code, String msg, String errorMsg) {
		Response<T> response = new Response<>();
		response.setSuccess(false);
		response.setCode(code);
		response.setMsg(msg);
		response.setErrorMsg(errorMsg);
		return response;
	}

	/**
	 * Fail response
	 *
	 * @param <T>      parameter
	 * @param code     code
	 * @param msg      msg
	 * @param errorMsg error msg
	 * @param data     data
	 * @return the response
	 */
	public static <T> Response<T> fail(long code, String msg, String errorMsg, T data) {
		Response<T> response = new Response<>();
		response.setSuccess(false);
		response.setCode(code);
		response.setMsg(msg);
		response.setErrorMsg(errorMsg);
		response.setData(data);
		return response;
	}

	/**
	 * Copy response
	 *
	 * @param <T>  parameter
	 * @param from from
	 * @return the response
	 */
	public static <T> Response<T> copy(Response<?> from) {
		Response<T> response = new Response<>();
		response.setSuccess(from.isSuccess());
		response.setCode(from.getCode());
		response.setMsg(from.getMsg());
		response.setErrorMsg(from.getErrorMsg());
		return response;
	}
}
