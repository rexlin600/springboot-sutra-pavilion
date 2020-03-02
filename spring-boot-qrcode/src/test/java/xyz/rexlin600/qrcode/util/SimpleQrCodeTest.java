package xyz.rexlin600.qrcode.util;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.SneakyThrows;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-02 14:23
 */
public class SimpleQrCodeTest {

    private static final String LOCAL_FILE = "C:\\Users\\hekunlin\\Pictures\\QRCode\\simple.png";

    /**
     * 生成简单二维码
     */
    @SneakyThrows
    @Test
    public void simpleQrCode() {
        BufferedImage bufferedImage = QrCodeUtil.simpleQrCode("This is a simple QRCode");
        QrCodeUtil.write2File(bufferedImage, "png", LOCAL_FILE);
    }

    /**
     * 生成指定宽高的二维码
     */
    @SneakyThrows
    @Test
    public void testSimpleQrCode() {
        BufferedImage bufferedImage = QrCodeUtil.simpleQrCode("This is a simple QRCode", 200, 200);
        QrCodeUtil.write2File(bufferedImage, "png", LOCAL_FILE);
    }

    /**
     * 生成指定宽高、编码、纠错等级的二维码
     */
    @SneakyThrows
    @Test
    public void testSimpleQrCode1() {
        BufferedImage bufferedImage = QrCodeUtil.simpleQrCode("This is a simple QRCode", 400, 400, "UTF-8", ErrorCorrectionLevel.H);
        QrCodeUtil.write2File(bufferedImage, "png", LOCAL_FILE);
    }

}