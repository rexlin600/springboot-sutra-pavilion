package xyz.rexlin600.common.enums;

/**
 * 返回报文Enum
 */
public enum ResponseEnum {
    SUCCESS(200, "请求成功"),
    INVALID_PARAMS(450, "无效参数"),
    EXECUTE_EXCEPTION(500, "系统异常"),
    /***
     * token过期
     */
    EXPIRED_TOKEN(2001, "token过期"),

    /***
     * 不合法的token
     */
    INVALID_TOKEN(2002, "不合法的token"),

    /***
     * 空的token
     */
    EMPTY_TOKEN(2003, "空的token"),

    OPERATION_FILED(20500, "操作失败"),
    ENTITY_NOT_FOUND(40404, "未查询到记录"),

    ROLE_EXCEPTION_ADD(10101, "角色新增失败"),
    ROLE_EXCEPTION_DELETE(10102, "角色删除失败"),
    ROLE_EXCEPTION_QUERY(10103, "角色查询异常"),
    ROLE_EXCEPTION_UPDATE(10104, "角色修改失败"),
    ROLE_EXCEPTION(10105, "角色操作异常"),
    ROLE_EXCEPTION_CODE_EXIST(10106, "角色代码重复"),

    ORG_EXCEPTION_ADD(10201, "组织机构新增失败"),
    ORG_EXCEPTION_DELETE(10202, "组织机构删除失败"),
    ORG_EXCEPTION_QUERY(10203, "组织机构查询异常"),
    ORG_EXCEPTION_UPDATE(10204, "组织机构修改失败"),
    ORG_EXCEPTION(10205, "组织机构操作异常"),
    ORG_EXCEPTION_CODE_EXIST(10206, "组织机构代码重复"),

    THRESHOLD_EXCEPTION_ADD(10301, "阈值配置新增失败"),
    THRESHOLD_EXCEPTION_DELETE(10302, "阈值配置删除失败"),
    THRESHOLD_EXCEPTION_QUERY(10303, "阈值配置查询异常"),
    THRESHOLD_EXCEPTION_UPDATE(10304, "阈值配置修改失败"),
    THRESHOLD_EXCEPTION_PARAMS_1(10305, "参数异常：个数，次数单位为正整数"),
    THRESHOLD_EXCEPTION_PARAMS_2(10306, "参数异常：百分比单位为正数"),
    THRESHOLD_EXCEPTION(10307, "阈值配置操作异常"),
    THRESHOLD_EXCEPTION_CODE_EXIST(10308, "该预警已配置"),

    UPLOAD_ATTACHMENT_COUNT(10400, "最多上传三个附件"),

    FILE_FORMAT_EXCEPTION(10501, "文件上传失败"),
    ENTERPRISE_CODE_EXIST(10502, "企业编码已存在"),
    ENTERPRISE_EXIST(10503, "企业编号或企业社会信用代码重复"),
    PLATFORM_CODE_EXIST(10504, "平台编号已存在"),
    IP_PORT_ILLEGAL(10505, "请输入合法的ip和端口"),
    IP_PORT_NOT_NULL(10506, "ip和端口不能为空"),

    /**
     * 开发平台官网
     */
    LOGIN_NAME_EMPTY(10601, "会员名不能为空"),
    MOBILE_EMPTY(10602, "手机号不能为空"),
    LOGIN_PASSWORD_EMPTY(10603, "密码不能为空"),
    LOGIN_VALIDATECODE_EMPTY(10604, "验证码不能为空"),
    LOGIN_NAME_EXIST(10605, "会员名已存在"),
    MOBILE_VALIDATE_ERROR(10606, "手机验证码不正确"),
    USER_REGISTER_ERROR(10607, "注册失败,请稍后再试"),
    LOGIN_VALIDATECODE_ERROR(10608, "验证码输入错误"),
    LOGIN_ERROR(10609, "登录失败，用户名或者密码错误"),
    UUID_EMPTY(10610, "登录失败，验证码错误"),
    REGISTER_USERID_EMPTY(10611, "注册失败"),
    REGISTER_USERID_TOKEN_ERROR(10612, "注册失败"),
    REGISTER_USERID_NOT_EXIST(10613, "注册失败"),
    REGISTER_ENTERPRISEINFO_SAVE_FAILED(10614, "注册失败"),
    REGISTER_USERBIND_SAVE_FAILED(10615, "注册失败"),
    REGISTER_USERBIND_EXIST(10616, "此账号已经注册过企业信息"),
    REGISTER_USEMOBILE_EXIST(10617, "此手机号已经注册过企业信息"),


    /**
     * 斑马联盟（商户）APP
     */
    LOGIN_FAILED_ENTERPRISE_NOT_EXIST(10700, "商户服务未开通"),
    LOGIN_FAILED_BRAND_NOT_EXIST(10701, "商户服务未开通");


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
