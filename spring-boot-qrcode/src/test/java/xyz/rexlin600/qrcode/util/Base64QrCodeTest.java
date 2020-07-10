package xyz.rexlin600.qrcode.util;

import lombok.SneakyThrows;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;

public class Base64QrCodeTest {

	private static final File LOGO_FILE = new File("C:\\Users\\hekunlin\\Pictures\\golang.jpg");

	@SneakyThrows
	@Test
	public void base64QrCode() {
		BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("test");
		String base64QrCode = QrCodeGenUtil.base64QrCode(bufferedImage);
		System.out.println(base64QrCode);
	}

	@SneakyThrows
	@Test
	public void base64QrCode1() {
		BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("test");
		bufferedImage = QrCodeGenUtil.logoQrCode(bufferedImage, LOGO_FILE);
		String base64QrCode = QrCodeGenUtil.base64QrCode(bufferedImage);
		System.out.println(base64QrCode);
	}

}