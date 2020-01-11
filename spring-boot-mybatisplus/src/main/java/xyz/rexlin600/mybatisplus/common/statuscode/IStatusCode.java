package xyz.rexlin600.github.common.statuscode;

/**
 * 状态码抽象接口
 */
public interface IStatusCode {

    /**
     * 状态码
     */
    long getCode();

    /**
     * 描述信息
     */
    String getMsg();
}
