package xyz.rexlin600.exception.base;

import lombok.Data;

/**
 * 基本异常
 *
 * @author: hekunlin
 * @date: 2020/1/20
 */
@Data
public class BaseException extends RuntimeException {

    private int code;

    private String msg;

    public BaseException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String message, int code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String message, Throwable cause, int code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(Throwable cause, int code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }
}