package xyz.rexlin600.qrcode.util;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.SneakyThrows;
import org.junit.Test;

import java.awt.image.BufferedImage;

public class SimpleQrCodeTest {

	private static final String LOCAL_FILE = "C:\\Users\\hekunlin\\Pictures\\QRCode\\simple.png";

	@SneakyThrows
	@Test
	public void simpleQrCode() {
		BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("This is a simple QRCode");
		QrCodeGenUtil.write2File(bufferedImage, "png", LOCAL_FILE);
	}

	@SneakyThrows
	@Test
	public void testSimpleQrCode() {
		BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("This is a simple QRCode", 200, 200);
		QrCodeGenUtil.write2File(bufferedImage, "png", LOCAL_FILE);
	}

	@SneakyThrows
	@Test
	public void testSimpleQrCode1() {
		BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("This is a simple QRCode", 400, 400, ErrorCorrectionLevel.H);
		QrCodeGenUtil.write2File(bufferedImage, "png", LOCAL_FILE);
	}

}