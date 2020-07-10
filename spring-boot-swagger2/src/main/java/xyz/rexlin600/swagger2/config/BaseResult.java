package xyz.rexlin600.swagger2.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Base result
 *
 * @param <T> parameter
 * @author hekunlin
 */
@ApiModel(description = "响应对象")
public class BaseResult<T> {
	/**
	 * SUCCESS_CODE
	 */
	private static final int SUCCESS_CODE = 0;
	/**
	 * SUCCESS_MESSAGE
	 */
	private static final String SUCCESS_MESSAGE = "成功";

	/**
	 * Code
	 */
	@ApiModelProperty(value = "响应码", name = "code", required = true, example = "" + SUCCESS_CODE)
	private int code;

	/**
	 * Msg
	 */
	@ApiModelProperty(value = "响应消息", name = "msg", required = true, example = SUCCESS_MESSAGE)
	private String msg;

	/**
	 * Data
	 */
	@ApiModelProperty(value = "响应数据", name = "data")
	private T data;

	/**
	 * Base result
	 *
	 * @param code code
	 * @param msg  msg
	 * @param data data
	 */
	private BaseResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * Base result
	 */
	private BaseResult() {
		this(SUCCESS_CODE, SUCCESS_MESSAGE);
	}

	/**
	 * Base result
	 *
	 * @param code code
	 * @param msg  msg
	 */
	private BaseResult(int code, String msg) {
		this(code, msg, null);
	}

	/**
	 * Base result
	 *
	 * @param data data
	 */
	private BaseResult(T data) {
		this(SUCCESS_CODE, SUCCESS_MESSAGE, data);
	}

	/**
	 * Success base result
	 *
	 * @param <T> parameter
	 * @return the base result
	 */
	public static <T> BaseResult<T> success() {
		return new BaseResult<>();
	}

	/**
	 * Success with data base result
	 *
	 * @param <T>  parameter
	 * @param data data
	 * @return the base result
	 */
	public static <T> BaseResult<T> successWithData(T data) {
		return new BaseResult<>(data);
	}

	/**
	 * Fail with code and msg base result
	 *
	 * @param <T>  parameter
	 * @param code code
	 * @param msg  msg
	 * @return the base result
	 */
	public static <T> BaseResult<T> failWithCodeAndMsg(int code, String msg) {
		return new BaseResult<>(code, msg, null);
	}

	/**
	 * Build with param base result
	 *
	 * @param <T>   parameter
	 * @param param param
	 * @return the base result
	 */
	public static <T> BaseResult<T> buildWithParam(ResponseParam param) {
		return new BaseResult<>(param.getCode(), param.getMsg(), null);
	}

	/**
	 * Gets code *
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets code *
	 *
	 * @param code code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets msg *
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets msg *
	 *
	 * @param msg msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets data *
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets data *
	 *
	 * @param data data
	 */
	public void setData(T data) {
		this.data = data;
	}


	/**
	 * Response param
	 */
	public static class ResponseParam {
		/**
		 * Code
		 */
		private int code;
		/**
		 * Msg
		 */
		private String msg;

		/**
		 * Response param
		 *
		 * @param code code
		 * @param msg  msg
		 */
		private ResponseParam(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		/**
		 * Build param response param
		 *
		 * @param code code
		 * @param msg  msg
		 * @return the response param
		 */
		public static ResponseParam buildParam(int code, String msg) {
			return new ResponseParam(code, msg);
		}

		/**
		 * Gets code *
		 *
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * Sets code *
		 *
		 * @param code code
		 */
		public void setCode(int code) {
			this.code = code;
		}

		/**
		 * Gets msg *
		 *
		 * @return the msg
		 */
		public String getMsg() {
			return msg;
		}

		/**
		 * Sets msg *
		 *
		 * @param msg msg
		 */
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
