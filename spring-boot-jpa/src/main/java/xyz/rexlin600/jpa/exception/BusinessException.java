package xyz.rexlin600.jpa.exception;

import lombok.Getter;
import lombok.Setter;
import xyz.rexlin600.jpa.common.enums.ResponseEnum;

/**
 * @author rexlin600
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 附加参数
     */
    private Object param;


    public BusinessException(ResponseEnum re) {
        super();
        set(re.getCode(), re.getMsg(), re.getMsg());
    }

    public BusinessException(ResponseEnum re, String msg) {
        super();
        set(re.getCode(), msg, msg);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
        if (throwable instanceof BusinessException) {
            BusinessException cast = (BusinessException) throwable;
            set(cast.code, cast.msg, cast.errorMsg);
            setParam(cast.param);
        } else {
            set(ResponseEnum.EXECUTE_EXCEPTION.getCode(), ResponseEnum.EXECUTE_EXCEPTION.getMsg(), throwable.toString());
        }
    }

    public BusinessException(Throwable throwable, ResponseEnum sc, String msg) {
        super(throwable);
        set(sc.getCode(), msg, msg);
    }

    public BusinessException(int code, String msg) {
        super();
        set(code, msg, msg);
    }

    public BusinessException(String msg) {
        super();
        set(ResponseEnum.EXECUTE_EXCEPTION.getCode(), msg, msg);
    }

    public BusinessException setParam(Object param) {
        this.param = param;
        return this;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(code);

        if (msg != null) {
            sb.append(", msg=").append(msg);
        }

        if (errorMsg != null) {
            sb.append(", errorMsg=").append(errorMsg);
        }

        if (param != null) {
            sb.append(", param=").append(param);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ":" + getMessage();
    }

    private void set(int code, String msg, String errorMsg) {
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }
}

