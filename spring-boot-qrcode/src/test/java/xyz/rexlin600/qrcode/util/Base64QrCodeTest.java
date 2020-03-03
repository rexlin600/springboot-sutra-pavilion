package xyz.rexlin600.qrcode.util;

import lombok.SneakyThrows;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-03 11:07
 */
public class Base64QrCodeTest {

    private static final File LOGO_FILE = new File("C:\\Users\\hekunlin\\Pictures\\golang.jpg");

    /**
     * 生成 Base64 的二维码
     */
    @SneakyThrows
    @Test
    public void base64QrCode() {
        BufferedImage bufferedImage = QrCodeUtil.simpleQrCode("test");
        String base64QrCode = QrCodeUtil.base64QrCode(bufferedImage);
        System.out.println(base64QrCode);
    }

    /**
     * 生成带 logo 的 Base64 二维码
     */
    @SneakyThrows
    @Test
    public void base64QrCode1() {
        BufferedImage bufferedImage = QrCodeUtil.simpleQrCode("test");
        bufferedImage = QrCodeUtil.logoQrCode(bufferedImage, LOGO_FILE);
        String base64QrCode = QrCodeUtil.base64QrCode(bufferedImage);
        System.out.println(base64QrCode);
    }

}