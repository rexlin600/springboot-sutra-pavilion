package xyz.rexlin600.jpa.exception;

import lombok.Getter;
import lombok.Setter;
import xyz.rexlin600.jpa.common.enums.ResponseEnum;

/**
 * Business exception
 *
 * @author hekunlin
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

	/**
	 * Code
	 */
	private int code;
	/**
	 * Msg
	 */
	private String msg;
	/**
	 * Error msg
	 */
	private String errorMsg;
	/**
	 * Param
	 */
	private Object param;


	/**
	 * Business exception
	 *
	 * @param re re
	 */
	public BusinessException(ResponseEnum re) {
		super();
		set(re.getCode(), re.getMsg(), re.getMsg());
	}

	/**
	 * Business exception
	 *
	 * @param re  re
	 * @param msg msg
	 */
	public BusinessException(ResponseEnum re, String msg) {
		super();
		set(re.getCode(), msg, msg);
	}

	/**
	 * Business exception
	 *
	 * @param throwable throwable
	 */
	public BusinessException(Throwable throwable) {
		super(throwable);
		if (throwable instanceof BusinessException) {
			BusinessException cast = (BusinessException) throwable;
			set(cast.code, cast.msg, cast.errorMsg);
			setParam(cast.param);
		} else {
			set(ResponseEnum.EXECUTE_EXCEPTION.getCode(), ResponseEnum.EXECUTE_EXCEPTION.getMsg(), throwable.toString());
		}
	}

	/**
	 * Business exception
	 *
	 * @param throwable throwable
	 * @param sc        sc
	 * @param msg       msg
	 */
	public BusinessException(Throwable throwable, ResponseEnum sc, String msg) {
		super(throwable);
		set(sc.getCode(), msg, msg);
	}

	/**
	 * Business exception
	 *
	 * @param code code
	 * @param msg  msg
	 */
	public BusinessException(int code, String msg) {
		super();
		set(code, msg, msg);
	}

	/**
	 * Business exception
	 *
	 * @param msg msg
	 */
	public BusinessException(String msg) {
		super();
		set(ResponseEnum.EXECUTE_EXCEPTION.getCode(), msg, msg);
	}

	/**
	 * Sets param *
	 *
	 * @param param param
	 * @return the param
	 */
	public BusinessException setParam(Object param) {
		this.param = param;
		return this;
	}

	/**
	 * Gets message *
	 *
	 * @return the message
	 */
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(code);

		if (msg != null) {
			sb.append(", msg=").append(msg);
		}

		if (errorMsg != null) {
			sb.append(", errorMsg=").append(errorMsg);
		}

		if (param != null) {
			sb.append(", param=").append(param);
		}

		return sb.toString();
	}

	/**
	 * To string string
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + ":" + getMessage();
	}

	/**
	 * Set *
	 *
	 * @param code     code
	 * @param msg      msg
	 * @param errorMsg error msg
	 */
	private void set(int code, String msg, String errorMsg) {
		this.code = code;
		this.msg = msg;
		this.errorMsg = errorMsg;
	}
}

