package xyz.rexlin600.qrcode.util;

import io.nayuki.qrcodegen.QrCode;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * QrCodeGen 二维码生成工具类
 *
 * @author: rexlin600
 * @date: 2020-02-28
 */
@Slf4j
public class QrCodeGenUtil {

    /**
     * 生成简单二维码
     *
     * @param content 二维码内容
     * @param ecc     二维码容错率
     * @param scale   二维码缩放变长
     * @param border  要添加的边界模块的数量，必须为而非负数
     * @return
     */
    public static BufferedImage genSimpleQRCode(String content, QrCode.Ecc ecc, int scale, int border) throws Exception {
        checkBorder(border);
        QrCode qr0 = QrCode.encodeText(content, ecc);
        BufferedImage bufferedImage = qr0.toImage(scale, border);
        return bufferedImage;
    }

    // -----------------------------------------------------------------------------------------------
    // Utilities
    // -----------------------------------------------------------------------------------------------

    /**
     * border 检查
     *
     * @param border
     */
    public static void checkBorder(int border) {
        if (border < 0) {
            throw new RuntimeException("border must > 0 !!!");
        }
    }

    /**
     * Helper function to reduce code duplication
     *
     * @param img        图片流
     * @param formatName 格式化文件名称，如：png、jpg
     * @param filepath   文件路径
     * @throws IOException
     */
    private static void writePic(BufferedImage img, String formatName, String filepath) throws IOException {
        ImageIO.write(img, formatName, new File(filepath));
    }

}
