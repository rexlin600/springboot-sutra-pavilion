package xyz.rexlin600.jpa.common.statuscode;

/**
 * Status code
 *
 * @author hekunlin
 */
public interface IStatusCode {

	/**
	 * Gets code *
	 *
	 * @return the code
	 */
	long getCode();

	/**
	 * Gets msg *
	 *
	 * @return the msg
	 */
	String getMsg();
}
