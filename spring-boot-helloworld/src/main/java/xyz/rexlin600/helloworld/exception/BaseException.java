package xyz.rexlin600.helloworld.exception;

/**
 * Base exception
 *
 * @author hekunlin
 */
public class BaseException extends RuntimeException {

	/**
	 * Base exception
	 */
	public BaseException() {
	}

	/**
	 * Base exception
	 *
	 * @param message message
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * Base exception
	 *
	 * @param message message
	 * @param cause   cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Base exception
	 *
	 * @param cause cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * Base exception
	 *
	 * @param message            message
	 * @param cause              cause
	 * @param enableSuppression  enable suppression
	 * @param writableStackTrace writable stack trace
	 */
	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}