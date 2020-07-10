package xyz.rexlin600.qrcode.util;

import lombok.SneakyThrows;
import org.junit.Test;
import xyz.rexlin600.qrcode.base.entity.QrCode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextQrCodeTest {

	private final static Font font = new Font("宋体", Font.BOLD, 32);
	private static final String LOCAL_FILE_PATH = "C:\\Users\\hekunlin\\Pictures\\text.png";

	@SneakyThrows
	@Test
	public void textQrCode() {
		BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("this is a text code");
		QrCode qrCode = new QrCode("测试文字二维码", "content", "131232dfwqf", "HikCreate Company LTD.", "231311312fqewrt14");
		bufferedImage = QrCodeGenUtil.textQrCode(qrCode, font, Color.BLACK);
		QrCodeGenUtil.write2File(bufferedImage, "png", LOCAL_FILE_PATH);
	}
}