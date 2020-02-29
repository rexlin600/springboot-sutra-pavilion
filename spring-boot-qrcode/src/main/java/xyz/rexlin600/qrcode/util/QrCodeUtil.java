package xyz.rexlin600.qrcode.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 二维码工具类
 *
 * @author: rexlin600
 * @date: 2020-02-28
 */
@Slf4j
public class QrCodeUtil {

    /**
     * 默认参数：二维码长度、二维码宽度、编码格式、纠错等级、二维码边框
     */
    private final static Integer QR_CODE_HEIGHT = 400;
    private final static Integer QR_CODE_WIDTH = 400;
    private final static String FORMAT = "UTF-8";
    private final static ErrorCorrectionLevel ERR_LEVEL = ErrorCorrectionLevel.M;
    private final static Integer MARGIN = 1;

    // -----------------------------------------------------------------------------------------------
    // SIMPLE QR CODE
    // -----------------------------------------------------------------------------------------------

    /**
     * 获取简单二维码缓冲图像
     *
     * @param content 二维码内容
     * @return {@link BufferedImage}
     */
    public static BufferedImage simpleQrCode(String content) {
        return simpleQrCode(content, QR_CODE_HEIGHT, QR_CODE_WIDTH, FORMAT, ERR_LEVEL, MARGIN);
    }

    /**
     * 获取简单二维码缓冲图像
     *
     * @param content 二维码内容
     * @param height  二维码长度
     * @param width   二维码宽度
     * @return {@link BufferedImage}
     */
    public static BufferedImage simpleQrCode(String content, int height, int width) {
        return simpleQrCode(content, height, width, FORMAT, ERR_LEVEL, MARGIN);
    }

    /**
     * 获取简单二维码缓冲图像
     *
     * @param content 二维码内容
     * @param height  二维码长度
     * @param width   二维码宽度
     * @param width   二维码边框
     * @return {@link BufferedImage}
     */
    public static BufferedImage simpleQrCode(String content, int height, int width, int margin) {
        return simpleQrCode(content, height, width, FORMAT, ERR_LEVEL, margin);
    }

    /**
     * 获取简单二维码缓冲图像
     *
     * @param content 二维码内容
     * @param height  二维码长度
     * @param width   二维码宽度
     * @param format  二维码格式化
     * @param level   二维码纠错等级
     * @param margin  二维码边框
     * @return {@link BufferedImage}
     */
    public static BufferedImage simpleQrCode(String content, int height, int width,
                                             String format, ErrorCorrectionLevel level, int margin) {
        // 内容检查
        check(content);

        // 检查二维码长宽
        checkHeightAndWidth(height, width);

        // 参数格式化
        format = StringUtils.isEmpty(format) ? "UTF-8" : format;
        level = Objects.isNull(level) ? ErrorCorrectionLevel.M : level;
        margin = margin <= 0 ? 1 : margin;

        // 编码提示类型配置
        Map<EncodeHintType, Object> map = new HashMap<>();
        map.put(EncodeHintType.CHARACTER_SET, format);
        map.put(EncodeHintType.ERROR_CORRECTION, level);
        map.put(EncodeHintType.MARGIN, margin);

        // 获取二维码位图矩阵
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, height, width, map);
        } catch (WriterException e) {
            log.error("获取二维码位图矩阵失败=【{}】", e.getMessage());
            throw new RuntimeException("获取二维码位图矩阵失败");
        }

        // 获取二维码图像配置
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);

        // 获取二维码缓冲图像
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);

        return bufferedImage;
    }

    // -----------------------------------------------------------------------------------------------
    // Logo QR CODE
    // -----------------------------------------------------------------------------------------------

    /**
     * 填充 logo 到二维码图片中
     *
     * @param qrCodeMatrixImage 原二维码图片矩阵
     * @param logoFile          logo图片
     * @return
     * @throws IOException
     */
    public static BufferedImage fillLogo2QrCode(BufferedImage qrCodeMatrixImage, File logoFile) throws IOException {
        BufferedImage logoMatrixImage = ImageIO.read(logoFile);
        BufferedImage bufferedImage = fillLogo2QrCode(qrCodeMatrixImage, logoMatrixImage);
        return bufferedImage;
    }

    /**
     * 填充 logo 到二维码图片中
     *
     * @param qrCodeMatrixImage 原二维码图片矩阵
     * @param inputStream       logo输入流
     * @return
     * @throws IOException
     */
    public static BufferedImage fillLogo2QrCode(BufferedImage qrCodeMatrixImage, InputStream inputStream) throws IOException {
        BufferedImage logoMatrixImage = ImageIO.read(inputStream);
        BufferedImage bufferedImage = fillLogo2QrCode(qrCodeMatrixImage, logoMatrixImage);
        return bufferedImage;
    }

    /**
     * 填充 logo 到二维码图片中
     *
     * @param qrCodeMatrixImage 原二维码图片矩阵
     * @param imageInputStream  logo图片输入流
     * @return
     * @throws IOException
     */
    public static BufferedImage fillLogo2QrCode(BufferedImage qrCodeMatrixImage, ImageInputStream imageInputStream) throws IOException {
        BufferedImage logoMatrixImage = ImageIO.read(imageInputStream);
        BufferedImage bufferedImage = fillLogo2QrCode(qrCodeMatrixImage, logoMatrixImage);
        return bufferedImage;
    }

    /**
     * 填充 logo 到二维码图片中
     *
     * @param qrCodeMatrixImage 原二维码图片矩阵
     * @param url               logo URL
     * @return
     * @throws IOException
     */
    public static BufferedImage fillLogo2QrCode(BufferedImage qrCodeMatrixImage, URL url) throws IOException {
        BufferedImage logoMatrixImage = ImageIO.read(url);
        BufferedImage bufferedImage = fillLogo2QrCode(qrCodeMatrixImage, logoMatrixImage);
        return bufferedImage;
    }

    /**
     * 填充 logo 到二维码图片中
     *
     * @param qrCodeMatrixImage 原二维码图片矩阵
     * @param logoMatrixImage   logo二维码图片矩阵
     * @return
     * @throws IOException
     */
    public static BufferedImage fillLogo2QrCode(BufferedImage qrCodeMatrixImage, BufferedImage logoMatrixImage) throws IOException {
        int height = qrCodeMatrixImage.getHeight();
        int width = qrCodeMatrixImage.getWidth();

        Graphics2D imageGraphics = null;
        try {
            // 读取二维码、构建图像
            imageGraphics = qrCodeMatrixImage.createGraphics();

            // logo 绘制
            imageGraphics.drawImage(logoMatrixImage, width / 5 * 2, height / 5 * 2, width / 5, height / 5, null);
            BasicStroke basicStroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            imageGraphics.setStroke(basicStroke);

            RoundRectangle2D.Float round = new RoundRectangle2D.Float(width / 5 * 2, height / 5 * 2, width / 5, height / 5, 20, 20);
            imageGraphics.setColor(Color.white);
            // 绘制圆弧矩形
            imageGraphics.draw(round);

            // 设置logo 有一道灰色边框
            BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            // 设置笔画对象
            imageGraphics.setStroke(stroke2);
            // 指定弧度的圆角矩形
            RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width / 5 * 2 + 2, height / 5 * 2 + 2, width / 5 - 4, height / 5 - 4, 20, 20);
            imageGraphics.setColor(new Color(128, 128, 128));

            // 绘制圆弧矩形
            imageGraphics.draw(round2);
        } finally {
            // 释放资源Ω
            imageGraphics.dispose();
            qrCodeMatrixImage.flush();
        }

        return qrCodeMatrixImage;
    }

    // -----------------------------------------------------------------------------------------------
    // TODO 生成带文字的二维码
    // -----------------------------------------------------------------------------------------------


    // -----------------------------------------------------------------------------------------------
    // TODO 批量打包
    // -----------------------------------------------------------------------------------------------


    // -----------------------------------------------------------------------------------------------
    // WRITE BufferedImage TO File/OutputStream/ImageOutputStream
    // -----------------------------------------------------------------------------------------------

    /**
     * 图片缓冲对象写入到文件
     *
     * @param img      图片缓冲对象
     * @param fileType 格式化文件名称，如：png、jpg
     * @param filepath 文件路径
     * @throws IOException
     */
    private static void write2File(BufferedImage img, String fileType, String filepath) throws IOException {
        ImageIO.write(img, fileType, new File(filepath));
    }

    /**
     * 图片缓冲对象写入到输出流
     *
     * @param img          图片流
     * @param fileType     格式化文件名称，如：png、jpg
     * @param outputStream 输出流
     * @throws IOException
     */
    private static void write2Stream(BufferedImage img, String fileType, OutputStream outputStream) throws IOException {
        ImageIO.write(img, fileType, outputStream);
    }

    /**
     * 图片缓冲对象写入到图片输出流
     *
     * @param img               图片流
     * @param fileType          格式化文件名称，如：png、jpg
     * @param imageOutputStream 图片输出流
     * @throws IOException
     */
    private static void write2Stream(BufferedImage img, String fileType, ImageOutputStream imageOutputStream) throws IOException {
        ImageIO.write(img, fileType, imageOutputStream);
    }

    // -----------------------------------------------------------------------------------------------
    // IDENTIFY QR CODE
    // -----------------------------------------------------------------------------------------------

    /**
     * 识别二维码内容
     *
     * @param url URL链接
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public static Result identifyQrCode(URL url) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(url);
        Result result = identifyQrCode(bufferedImage);
        return result;
    }

    /**
     * 识别二维码内容
     *
     * @param file 文件
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public static Result identifyQrCode(File file) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(file);
        Result result = identifyQrCode(bufferedImage);
        return result;
    }

    /**
     * 识别二维码内容
     *
     * @param inputStream 输入流
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public static Result identifyQrCode(InputStream inputStream) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        Result result = identifyQrCode(bufferedImage);
        return result;
    }

    /**
     * 识别二维码内容
     *
     * @param imageInputStream 图片输入流
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public static Result identifyQrCode(ImageInputStream imageInputStream) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(imageInputStream);
        Result result = identifyQrCode(bufferedImage);
        return result;
    }

    /**
     * 识别二维码内容
     *
     * @param bufferedImage 缓冲图像
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public static Result identifyQrCode(BufferedImage bufferedImage) throws IOException, NotFoundException {
        Result result = null;
        try {
            // 读取指定的二维码文件
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

            // 定义二维码参数
            Map hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            result = new MultiFormatReader().decode(binaryBitmap, hints);

            // 输出相关的二维码信息
            log.info("解析二维码，二维码格式类型=【{}】、二维码内容=【{}】", result.getBarcodeFormat(), result.getText());
        } finally {
            // 释放资源
            bufferedImage.flush();
        }

        return result;
    }

    // -----------------------------------------------------------------------------------------------
    // Utilities
    // -----------------------------------------------------------------------------------------------

    /**
     * 内容检查
     *
     * @param obj
     */
    public static void check(Object obj) throws RuntimeException {
        if ((obj instanceof String) && (StringUtils.isEmpty(obj))) {
            throw new RuntimeException("param can not be null or empty");
        }
        if ((obj instanceof BufferedImage) && (obj == null)) {
            throw new RuntimeException("param can not be is null");
        }
    }

    /**
     * 二维码长宽检查
     *
     * @param height
     * @param width
     * @throws RuntimeException
     */
    public static void checkHeightAndWidth(int height, int width) throws RuntimeException {
        if (height < 0 || width < 0) {
            throw new RuntimeException("param must > 0");
        }
    }

}