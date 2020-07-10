package xyz.rexlin600.qrcode.base.constants;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Qr code constant
 *
 * @author hekunlin
 */
public final class QrCodeConstant {

	/**
	 * QR_CODE_HEIGHT
	 */
	public final static Integer QR_CODE_HEIGHT = 400;

	/**
	 * QR_CODE_WIDTH
	 */
	public final static Integer QR_CODE_WIDTH = 400;

	/**
	 * FORMAT
	 */
	public final static String FORMAT = "UTF-8";

	/**
	 * ERR_LEVEL
	 */
	public final static ErrorCorrectionLevel ERR_LEVEL = ErrorCorrectionLevel.M;

	/**
	 * MARGIN
	 */
	public final static Integer MARGIN = 3;

	/**
	 * FRONT_COLOR
	 */
	public static final int FRONT_COLOR = 0x000000;

	/**
	 * BACKGROUND_COLOR
	 */
	public static final int BACKGROUND_COLOR = 0xFFFFFF;

	/**
	 * JPG
	 */
	public static final String JPG = "jpg";

	/**
	 * PNG
	 */
	public static final String PNG = "png";

}