package xyz.rexlin600.oss.common;

/**
 * OSS 常量类
 *
 * @author: hekunlin
 * @since: 2020/6/22
 */
public class OssConstant {

    /**
     * OSS path 处理常量
     */
    public static final String SLASH = "/";
    public static final String SLASH_PLUS = "/+";

    /**
     * 10M 带宽（China）
     */
    public static final Integer BANDWIDTH_10MB = 80;

    /**
     * OSS 配置
     */
    public static final String ALI_CONFIG = "aliOssConfig";
    public static final String TX_CONFIG = "txOssConfig";
    public static final String QN_CONFIG = "qnOssConfig";

    /**
     * 前缀配置
     */
    public static final String PREFIX_ALI = "oss.aliyun";
    public static final String PREFIX_TX = "oss.tencent";
    public static final String PREFIX_QN = "oss.qiniu";

}