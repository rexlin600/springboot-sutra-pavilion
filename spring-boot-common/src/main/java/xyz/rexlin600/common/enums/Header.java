package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Header
 *
 * @author hekunlin
 */
@NoArgsConstructor
@Getter
public enum Header {

	/**
	 * Date header
	 */
	DATE("Date"),
	/**
	 * Connection header
	 */
	CONNECTION("Connection"),
	/**
	 * Mime version header
	 */
	MIME_VERSION("MIME-Version"),
	/**
	 * Trailer header
	 */
	TRAILER("Trailer"),
	/**
	 * Transfer encoding header
	 */
	TRANSFER_ENCODING("Transfer-Encoding"),
	/**
	 * Upgrade header
	 */
	UPGRADE("Upgrade"),
	/**
	 * Via header
	 */
	VIA("Via"),
	/**
	 * Cache control header
	 */
	CACHE_CONTROL("Cache-Control"),
	/**
	 * Pragma header
	 */
	PRAGMA("Pragma"),
	/**
	 * Content type header
	 */
	CONTENT_TYPE("Content-Type"),
	/**
	 * Host header
	 */
	HOST("Host"),
	/**
	 * Referer header
	 */
	REFERER("Referer"),
	/**
	 * Origin header
	 */
	ORIGIN("Origin"),
	/**
	 * User agent header
	 */
	USER_AGENT("User-Agent"),
	/**
	 * Accept header
	 */
	ACCEPT("Accept"),
	/**
	 * Accept language header
	 */
	ACCEPT_LANGUAGE("Accept-Language"),
	/**
	 * Accept encoding header
	 */
	ACCEPT_ENCODING("Accept-Encoding"),
	/**
	 * Accept charset header
	 */
	ACCEPT_CHARSET("Accept-Charset"),
	/**
	 * Cookie header
	 */
	COOKIE("Cookie"),
	/**
	 * Content length header
	 */
	CONTENT_LENGTH("Content-Length"),
	/**
	 * Set cookie header
	 */
	SET_COOKIE("Set-Cookie"),
	/**
	 * Content encoding header
	 */
	CONTENT_ENCODING("Content-Encoding"),
	/**
	 * Content disposition header
	 */
	CONTENT_DISPOSITION("Content-Disposition"),
	/**
	 * Etag header
	 */
	ETAG("ETag"),
	/**
	 * Location header
	 */
	LOCATION("Location");

	/**
	 * Value
	 */
	private String value;

	/**
	 * Header
	 *
	 * @param value value
	 */
	Header(String value) {
		this.value = value;
	}

}
