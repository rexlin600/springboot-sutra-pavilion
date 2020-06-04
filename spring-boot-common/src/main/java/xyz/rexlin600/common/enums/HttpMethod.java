package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hekunlin
 */

@Getter
@NoArgsConstructor
public enum HttpMethod {

    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PUT("PUT"),
    DELETE("DELETE"),
    TRACE("TRACE"),
    CONNECT("CONNECT"),
    PATCH("PATCH");

    private String value;

    HttpMethod(String value) {
        this.value = value;
    }
}
