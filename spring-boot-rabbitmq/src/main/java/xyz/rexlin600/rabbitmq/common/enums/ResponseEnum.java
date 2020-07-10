package xyz.rexlin600.rabbitmq.common.enums;

/**
 * Response enum
 *
 * @author hekunlin
 */
public enum ResponseEnum {

	/**
	 * Execute exception response enum
	 */
	EXECUTE_EXCEPTION(500, "系统异常");

	/**
	 * Code
	 */
	private int code;
	/**
	 * Msg
	 */
	private String msg;

	/**
	 * Response enum
	 *
	 * @param code code
	 * @param msg  msg
	 */
	ResponseEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
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
	 * Gets msg *
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
}
