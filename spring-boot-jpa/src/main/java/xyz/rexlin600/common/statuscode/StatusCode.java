package xyz.rexlin600.common.statuscode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公共状态码
 */
@Getter
@AllArgsConstructor
public enum StatusCode implements IStatusCode {

    SUCCESS(1000, "操作成功", "操作成功"),
    SYSTEM_ERROR(1001, "系统出现错误[1001]", "系统未知错误"),
    PERMISSION_DENIED(1002, "没有权限", "没有权限"),
    DATA_ERROR(1003, "数据错误", "数据错误"),
    REPEAT_SUBMIT(1004, "重复提交", "重复提交"),
    PARAM_ERROR(1005, "参数错误", "参数错误"),
    BUSINESS_ERROR(1006, "业务错误", "业务错误"),
    SIGN_ERROR(1007, "登录失效", "签名错误"),
    NO_TOKEN(1008, "登录失效，请重新登录", "缺少Token"),
    CROWDED_ERROR(1009, "被挤下线", "被挤下线"),
    INVALID_TOKEN(1010, "登录失效，请重新登录", "非法Token"),
    EXPIRED_TOKEN(1011, "登录失效，请重新登录", "过期Token"),
    OUTDATE_WEAPP_VERSION(1012, "有更新的版本，请重启小程序", "过时的微信版本"),
    ACCOUNT_IS_FROZEN(1013, "用户账号被冻结", "用户账号被冻结"),
    INVALID_REQUEST(1014, "非法的请求", "非法请求, token时间戳异常"),
    ALERT_ERROR(1015, "业务错误", "弹框的业务错误"),
    NOT_FOUND_DATA(1020, "没有这个数据", "系统没有找到这个数据"),
    APPROVED(1021, "已通过审核，请勿重复提交", "已通过审核，请勿重复提交"),
    ;

    /**
     * 状态码
     */
    private long code;

    /**
     * 描述信息
     */
    private String msg;

    private String errorMsg;
}
