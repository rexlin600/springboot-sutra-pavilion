package xyz.rexlin600.qrcode.base.constants;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码常量类
 *
 * @author: hekunlin
 * @date: 2020/3/3
 */
public final class QrCodeConstant {

    /** 二维码长度 */
    public final static Integer QR_CODE_HEIGHT = 400;

    /** 二维码宽度 */
    public final static Integer QR_CODE_WIDTH = 400;

    /** 编码格式 */
    public final static String FORMAT = "UTF-8";

    /** 纠错等级 */
    public final static ErrorCorrectionLevel ERR_LEVEL = ErrorCorrectionLevel.M;

    /** 固定二维码边框 */
    public final static Integer MARGIN = 3;

    /** 前景色-黑 */
    public static final int FRONT_COLOR = 0x000000;

    /** 背景色-白 */
    public static final int BACKGROUND_COLOR = 0xFFFFFF;

    /** JPG */
    public static final String JPG = "jpg";

    /** PNG */
    public static final String PNG = "png";

}