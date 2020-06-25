package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 请求头枚举类
 *
 * @author rexlin600
 */

@NoArgsConstructor
@Getter
public enum Header {

    /**
     * Date
     */
    DATE("Date"),
    /**
     * Connection
     */
    CONNECTION("Connection"),
    /**
     * MIME-Version
     */
    MIME_VERSION("MIME-Version"),
    /**
     * Trailer
     */
    TRAILER("Trailer"),
    /**
     * Transfer-Encoding
     */
    TRANSFER_ENCODING("Transfer-Encoding"),
    /**
     * Upgrade
     */
    UPGRADE("Upgrade"),
    /**
     * Via
     */
    VIA("Via"),
    /**
     * Cache-Control
     */
    CACHE_CONTROL("Cache-Control"),
    /**
     * Pragma
     */
    PRAGMA("Pragma"),
    /**
     * Content-Type
     */
    CONTENT_TYPE("Content-Type"),
    /**
     * Host
     */
    HOST("Host"),
    /**
     * Referer
     */
    REFERER("Referer"),
    /**
     * Origin
     */
    ORIGIN("Origin"),
    /**
     * User-Agent
     */
    USER_AGENT("User-Agent"),
    /**
     * Accept
     */
    ACCEPT("Accept"),
    /**
     * Accept-Language
     */
    ACCEPT_LANGUAGE("Accept-Language"),
    /**
     * Accept-Encoding
     */
    ACCEPT_ENCODING("Accept-Encoding"),
    /**
     * Accept-Charset
     */
    ACCEPT_CHARSET("Accept-Charset"),
    /**
     * Cookie
     */
    COOKIE("Cookie"),
    /**
     * Content-Length
     */
    CONTENT_LENGTH("Content-Length"),
    /**
     * Set-Cookie
     */
    SET_COOKIE("Set-Cookie"),
    /**
     * Content-Encoding
     */
    CONTENT_ENCODING("Content-Encoding"),
    /**
     * Content-Disposition
     */
    CONTENT_DISPOSITION("Content-Disposition"),
    /**
     * ETag
     */
    ETAG("ETag"),
    /**
     * Location
     */
    LOCATION("Location");

    /**
     * 数据值
     */
    private String value;

    Header(String value) {
        this.value = value;
    }

}
