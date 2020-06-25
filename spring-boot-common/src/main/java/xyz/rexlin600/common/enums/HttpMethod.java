package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * HTTP 请求方法枚举类
 *
 * @author hekunlin
 */

@Getter
@NoArgsConstructor
public enum HttpMethod {

    /**
     * GET
     */
    GET("GET"),
    /**
     * POST
     */
    POST("POST"),
    /**
     * HEAD
     */
    HEAD("HEAD"),
    /**
     * OPTIONS
     */
    OPTIONS("OPTIONS"),
    /**
     * PUT
     */
    PUT("PUT"),
    /**
     * DELETE
     */
    DELETE("DELETE"),
    /**
     * TRACE
     */
    TRACE("TRACE"),
    /**
     * CONNECT
     */
    CONNECT("CONNECT"),
    /**
     * PATCH
     */
    PATCH("PATCH");

    /**
     * 数据值
     */
    private String value;

    HttpMethod(String value) {
        this.value = value;
    }
}
