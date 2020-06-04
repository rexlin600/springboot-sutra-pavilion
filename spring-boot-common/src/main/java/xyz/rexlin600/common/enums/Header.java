package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum Header {

    DATE("Date"),
    CONNECTION("Connection"),
    MIME_VERSION("MIME-Version"),
    TRAILER("Trailer"),
    TRANSFER_ENCODING("Transfer-Encoding"),
    UPGRADE("Upgrade"),
    VIA("Via"),
    CACHE_CONTROL("Cache-Control"),
    PRAGMA("Pragma"),
    CONTENT_TYPE("Content-Type"),
    HOST("Host"),
    REFERER("Referer"),
    ORIGIN("Origin"),
    USER_AGENT("User-Agent"),
    ACCEPT("Accept"),
    ACCEPT_LANGUAGE("Accept-Language"),
    ACCEPT_ENCODING("Accept-Encoding"),
    ACCEPT_CHARSET("Accept-Charset"),
    COOKIE("Cookie"),
    CONTENT_LENGTH("Content-Length"),
    SET_COOKIE("Set-Cookie"),
    CONTENT_ENCODING("Content-Encoding"),
    CONTENT_DISPOSITION("Content-Disposition"),
    ETAG("ETag"),
    LOCATION("Location");

    private String value;

    Header(String value) {
        this.value = value;
    }
    
}
