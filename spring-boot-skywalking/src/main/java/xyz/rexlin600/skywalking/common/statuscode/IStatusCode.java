package xyz.rexlin600.skywalking.common.statuscode;

/**
 * 状态码抽象接口
 *
 * @author rexlin600
 */
public interface IStatusCode {

    /**
     * 状态码
     *
     * @return
     */
    long getCode();

    /**
     * 描述信息
     *
     * @return
     */
    String getMsg();
}
