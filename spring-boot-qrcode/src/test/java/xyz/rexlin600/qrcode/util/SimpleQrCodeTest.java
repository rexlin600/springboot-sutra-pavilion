package xyz.rexlin600.qrcode.util;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.SneakyThrows;
import org.junit.Test;

import java.awt.image.BufferedImage;

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
        BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("This is a simple QRCode");
        QrCodeGenUtil.write2File(bufferedImage, "png", LOCAL_FILE);
    }

    /**
     * 生成指定宽高的二维码
     */
    @SneakyThrows
    @Test
    public void testSimpleQrCode() {
        BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("This is a simple QRCode", 200, 200);
        QrCodeGenUtil.write2File(bufferedImage, "png", LOCAL_FILE);
    }

    /**
     * 生成指定宽高、编码、纠错等级的二维码
     */
    @SneakyThrows
    @Test
    public void testSimpleQrCode1() {
        BufferedImage bufferedImage = QrCodeGenUtil.simpleQrCode("This is a simple QRCode", 400, 400, ErrorCorrectionLevel.H);
        QrCodeGenUtil.write2File(bufferedImage, "png", LOCAL_FILE);
    }

}