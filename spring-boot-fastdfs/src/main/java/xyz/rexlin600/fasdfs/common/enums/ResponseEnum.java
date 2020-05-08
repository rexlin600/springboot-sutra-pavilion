package xyz.rexlin600.fasdfs.common.enums;

/**
 * 返回报文Enum
 *
 * @author rexlin600
 */
public enum ResponseEnum {

    /**
     * 系统异常
     */
    EXECUTE_EXCEPTION(500, "系统异常");


    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
