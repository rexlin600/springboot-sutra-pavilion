package xyz.rexlin600.qrcode.util;

import lombok.SneakyThrows;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-02 14:41
 */
public class TextQrCodeTest {

    private final static Font font = new Font("宋体", Font.BOLD, 32);
    private static final String LOCAL_FILE_PATH = "C:\\Users\\hekunlin\\Pictures\\QRCode\\text.png";

    /**
     * 生成带文字的二维码
     */
    @SneakyThrows
    @Test
    public void textQrCode() {
        BufferedImage bufferedImage = QrCodeUtil.simpleQrCode("this is a text code");
        bufferedImage = QrCodeUtil.textQrCode(bufferedImage, font, Color.BLACK, "131232dfwqf", "HikCreate Company LTD.", "231311312fqewrt14");
        QrCodeUtil.write2File(bufferedImage, "png", LOCAL_FILE_PATH);
    }
}