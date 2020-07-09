package xyz.rexlin600.fasdfs.common.statuscode;

/**
 * 状态码抽象接口
 *
 * @author rexlin600
 */
public interface IStatusCode {

    /**
     * 状态码
     *
     * @return {@link Long}
     */
    long getCode();

    /**
     * 描述信息
     *
     * @return {@link String}
     */
    String getMsg();
}
