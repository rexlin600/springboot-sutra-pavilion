package xyz.rexlin600.qrcode.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Zxing 二维码工具类
 *
 * @author: rexlin600
 * @date: 2020-02-28
 */
public class ZxingQRCodeUtil {

    /**
     * 默认配置
     * <p>
     * CODE_WIDTH：二维码宽度，单位像素
     * CODE_HEIGHT：二维码高度，单位像素
     * FRONT_COLOR：二维码前景色，0x000000 表示黑色
     * BACKGROUND_COLOR：二维码背景色，0xFFFFFF 表示白色
     * <p>
     * 演示用 16 进制表示，和前端页面 CSS 的取色是一样的，注意前后景颜色应该对比明显，如常见的黑白
     */
    private static final int CODE_WIDTH = 400;
    private static final int CODE_HEIGHT = 400;
    private static final int FRONT_COLOR = 0x000000;
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    /**
     * 生成 BASE64 的图片
     *
     * @param content    二维码内容
     * @param topFont    顶部文字
     * @param centerFont 中心文字
     * @param bottomFont 底部文字
     * @return
     * @throws Exception
     */
    public static String createBase64ImgStr(String content, String topFont, String centerFont, String bottomFont) throws Exception {
        BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        if (content == null || "".equals(content)) {
            return null;
        }
        topFont = topFont == null ? "" : topFont;
        centerFont = centerFont == null ? "" : centerFont;
        bottomFont = bottomFont == null ? "" : bottomFont;

        bufferedImage = pressTextInImage(bufferedImage, "", content, topFont, centerFont, bottomFont);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //写入流中
        ImageIO.write(bufferedImage, "jpg", outputStream);
        //转换成字节
        byte[] bytes = outputStream.toByteArray();
        //对字节数组Base64编码  -- 转换成base64串
	   /* BASE64Encoder encoder = new BASE64Encoder();
	    String base64Img = encoder.encodeBuffer(bytes).trim();*/
        Base64 encoder = new Base64();
        String base64Img = encoder.encodeToString(bytes);

        //删除 \r\n
        base64Img = base64Img.replaceAll("\n", "").replaceAll("\r", "");
        //前面加 data:image/jpg;base64,
        String res = "data:image/jpg;base64," + base64Img.toString();
        //关闭流
        if (outputStream != null) {
            outputStream.close();
        }
        System.out.println(res);

        return res;
    }

    // -----------------------------------------------------------------------------------------------
    // Utilities
    // -----------------------------------------------------------------------------------------------

    /**
     * 检查
     *
     * @param content
     * @param bufferedImage
     */
    public static void check(String content, BufferedImage bufferedImage) throws RuntimeException {
        if (StringUtils.isEmpty(content)) {
            throw new RuntimeException("content is null or empty");
        }
        if (bufferedImage == null) {
            throw new RuntimeException("bufferedImage is null");
        }
    }

    /**
     * 构建二维码
     *
     * @param content          二维码内容
     * @param bufferedImage    图片流
     * @param topFontWidth     顶部文字宽度
     * @param topFontHeight    顶部文字高度
     * @param centerFontWidth  中心文字宽度
     * @param centerFontHeight 中心文字高度
     * @param bottomFontWidth  底部文字宽度
     * @param bottomFontHeight 底部文字高度
     * @return
     * @throws WriterException
     */
    public static BufferedImage createQRCode(String content,
                                             BufferedImage bufferedImage,
                                             int topFontWidth, int topFontHeight,
                                             int centerFontWidth, int centerFontHeight,
                                             int bottomFontWidth, int bottomFontHeight) throws Exception {
        //检验参数
        check(content, bufferedImage);

        content = content.trim();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int startBottomY = height + topFontHeight;
        height = startBottomY + bottomFontHeight;

        //计算中文文字空白区域
        int centerFontStartX = (width - centerFontWidth) / 2 + 5;   // x轴起始坐标
        int centerFontEndX = centerFontStartX + centerFontWidth + 5;    // x轴终点坐标
        int centerFontStartY = (height - centerFontHeight) / 2 + 5; // y轴起始坐标
        int centerFontEndY = centerFontStartY + centerFontHeight + 5;   // y轴终点坐标

        /**
         * com.google.zxing.EncodeHintType：编码提示类型,枚举类型
         * EncodeHintType.CHARACTER_SET：设置字符编码类型
         * EncodeHintType.ERROR_CORRECTION：设置误差校正
         * ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
         * 不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
         * EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
         * */
        Map<EncodeHintType, Object> hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);

        /**
         * MultiFormatWriter:多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码
         * encode(String contents,BarcodeFormat format,int width, int height,Map<EncodeHintType,?> hints)
         *  contents:条形码/二维码内容
         *  format：编码类型，如 条形码，二维码 等
         *  width：码的宽度
         *  height：码的高度
         *  hints：码内容的编码类型
         * BarcodeFormat：枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
         * BitMatrix：位(比特)矩阵或叫2D矩阵，也就是需要的二维码
         */
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        /**java.awt.image.BufferedImage：具有图像数据的可访问缓冲图像，实现了 RenderedImage 接口
         * BitMatrix 的 get(int x, int y) 获取比特矩阵内容，指定位置有值，则返回true，将其设置为前景色，否则设置为背景色
         * BufferedImage 的 setRGB(int x, int y, int rgb) 方法设置图像像素
         * x：像素位置的横坐标，即列
         * y：像素位置的纵坐标，即行
         * rgb：像素的值，采用 16 进制,如 0xFFFFFF 白色
         */
        BufferedImage newBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //顶部文字填充区域设置为空白
                if (y <= topFontHeight) {
                    newBufferedImage.setRGB(x, y, BACKGROUND_COLOR);
                }
                //中心文字填充区域设置为空白
                else if (x > centerFontStartX && x < centerFontEndX && y > centerFontStartY && y < centerFontEndY) {
                    newBufferedImage.setRGB(x, y, BACKGROUND_COLOR);
                }
                //底部文字填充区域设置为空白
                else if (y > startBottomY) {
                    newBufferedImage.setRGB(x, y, BACKGROUND_COLOR);
                } else {
                    newBufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
                }

            }
        }
        //主动释放
        bufferedImage = null;
        return newBufferedImage;
    }

    /**
     * 文字填充至二维码
     *
     * @param bufferedImage 图片流
     * @param content       二维码内容
     * @param topFont       顶部文字
     * @param centerFont    中间文字
     * @param bottomFont    底部文字
     * @return
     * @throws Exception
     */
    public static BufferedImage pressTextInImage(BufferedImage bufferedImage,
                                                 String fontType,
                                                 String content,
                                                 String topFont,
                                                 String centerFont,
                                                 String bottomFont) throws Exception {
        check(content, bufferedImage);

        //图片绘制对象
        Graphics graphics = bufferedImage.createGraphics();
        Font font = new Font(fontType, 5, 32);
        Font font1 = new Font(fontType, 5, 23);
        FontMetrics metrics = graphics.getFontMetrics(font);
        FontMetrics metrics1 = graphics.getFontMetrics(font1);
        //获取中心字体的宽和高
        int centerFontWidth = metrics.stringWidth(centerFont);
        int centerFontHeight = metrics.getHeight();
        //获取底部字体的宽和高
        int bottomFontWidth = metrics1.stringWidth(bottomFont);
        int bottomFontHeight = metrics1.getHeight();
        //获取顶部字体的宽和高
        int topFontWidth = metrics1.stringWidth(topFont);
        int topFontHeight = metrics1.getHeight();
        //将image生成二维码图片对象
        bufferedImage = createQRCode(content, bufferedImage, topFontWidth, topFontHeight, centerFontWidth, centerFontHeight, bottomFontWidth, bottomFontHeight);
        //获取二维码图片的宽和高
        int imageW = bufferedImage.getWidth();
        int imageH = bufferedImage.getHeight();
        //计算顶部文字填充位置
        int topStartX = (imageW - topFontWidth) / 2; //居中显示
        int topStartY = topFontHeight;
        //计算中心文字填充位置
        int centerStartX = (imageW - centerFontWidth) / 2 + 10;  //居中显示
        int centerStartY = imageH / 2 + centerFontHeight / 2 - (centerFontHeight / 4) + 10;
        //计算底部文字填充位置
        int bottomStartX = (imageW - bottomFontWidth) / 2; //居中显示
        int bottomStartY = (imageH - bottomFontHeight) + 2;

        //文字图片对象
        BufferedImage textImag = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
        Graphics textGraphics = textImag.createGraphics();
        //画图
        textGraphics.drawImage(bufferedImage, 0, 0, imageW, imageH, null);
        //
        //设置中心画笔的颜色
        textGraphics.setColor(Color.BLACK);
        textGraphics.setFont(font);
        //  写中心字体
        textGraphics.drawString(centerFont, centerStartX, centerStartY);
        // 写底部文字
        textGraphics.setColor(Color.BLACK);
        textGraphics.setFont(font1);
        textGraphics.drawString(bottomFont, bottomStartX, bottomStartY);
        //写顶部字体
        textGraphics.setColor(Color.BLACK);
        textGraphics.setFont(font1);
        textGraphics.drawString(topFont, topStartX, topStartY);
        graphics.dispose();

        return textImag;
    }

    // -----------------------------------------------------------------------------------------------
    // Test
    // -----------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        Long startTime = System.currentTimeMillis();
        String content = "good";
        createBase64ImgStr(content, "XXX亲启", "从前车马慢", "");
        Long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println((endTime - startTime) + " |" + (endTime - startTime) / 1000);
    }

}