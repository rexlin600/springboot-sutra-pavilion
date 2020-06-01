package xyz.rexlin600.validation.common.apiparam;

import lombok.Data;
import xyz.rexlin600.validation.common.statuscode.StatusCode;

/**
 * 统一的返回结果，作为服务间进行通信的数据协议。<br/>
 * code表示状态码，不同服务不同接口会返回不同的状态码<br/>
 * msg表示返回的结果信息描述<br/>
 * errorMsg表示返回的错误结果信息描述<br/>
 * data作为泛型参数类型，在不同接口可以定义不同的类型
 *
 * @param <T> 数据泛型类型
 * @author rexlin600
 */
@Data
public class Response<T> {

    /**
     * 成功标记
     */
    private boolean success = true;

    /**
     * 状态码
     */
    private long code = StatusCode.SUCCESS.getCode();

    /**
     * 描述
     */
    private String msg = StatusCode.SUCCESS.getMsg();

    /**
     * 错误描述
     */
    private String errorMsg = StatusCode.SUCCESS.getMsg();

    /**
     * 挂载数据
     */
    private T data;

}
