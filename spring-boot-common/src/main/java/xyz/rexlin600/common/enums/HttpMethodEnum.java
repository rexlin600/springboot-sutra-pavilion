package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Http method
 *
 * @author hekunlin
 */
@Getter
@NoArgsConstructor
public enum HttpMethodEnum {

	/**
	 * Get http method
	 */
	GET("GET"),
	/**
	 * Post http method
	 */
	POST("POST"),
	/**
	 * Head http method
	 */
	HEAD("HEAD"),
	/**
	 * Options http method
	 */
	OPTIONS("OPTIONS"),
	/**
	 * Put http method
	 */
	PUT("PUT"),
	/**
	 * Delete http method
	 */
	DELETE("DELETE"),
	/**
	 * Trace http method
	 */
	TRACE("TRACE"),
	/**
	 * Connect http method
	 */
	CONNECT("CONNECT"),
	/**
	 * Patch http method
	 */
	PATCH("PATCH");

	/**
	 * Value
	 */
	private String value;

	/**
	 * Http method
	 *
	 * @param value value
	 */
	HttpMethodEnum(String value) {
		this.value = value;
	}
}
