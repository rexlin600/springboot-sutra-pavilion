package xyz.rexlin600.qrcode.util;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import sun.font.FontDesignMetrics;
import xyz.rexlin600.qrcode.base.constants.QrCodeConstant;
import xyz.rexlin600.qrcode.base.entity.BatchQrCode;
import xyz.rexlin600.qrcode.base.entity.QrCode;
import xyz.rexlin600.qrcode.base.enums.TextPositionEnum;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.List;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Qr code gen util
 *
 * @author hekunlin
 */
@SuppressWarnings("DuplicatedCode")
@Slf4j
public class QrCodeGenUtil {

	/**
	 * NUM_8
	 */
	private final static Integer NUM_8 = 8;
	/**
	 * NUM_20
	 */
	private final static Integer NUM_20 = 20;
	/**
	 * NUM_15
	 */
	private final static Integer NUM_15 = 15;
	/**
	 * NUM_24
	 */
	private final static Integer NUM_24 = 24;

	/**
	 * THREAD_POOL_EXECUTOR
	 */
	private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
			5,
			10,
			10,
			TimeUnit.SECONDS,
			new LinkedBlockingDeque<>(),
			new ThreadFactoryBuilder().setNamePrefix("qrcode-pool-%d").build(),
			new ThreadPoolExecutor.AbortPolicy());

	// -----------------------------------------------------------------------------------------------
	// SIMPLE QR CODE
	// -----------------------------------------------------------------------------------------------

	/**
	 * Simple qr code buffered image
	 *
	 * @param content content
	 * @return the buffered image
	 */
	@SneakyThrows
	public static BufferedImage simpleQrCode(String content) {
		return simpleQrCode(content, QrCodeConstant.QR_CODE_HEIGHT, QrCodeConstant.QR_CODE_WIDTH, QrCodeConstant.ERR_LEVEL);
	}

	/**
	 * Simple qr code buffered image
	 *
	 * @param content content
	 * @param height  height
	 * @param width   width
	 * @return the buffered image
	 */
	@SneakyThrows
	public static BufferedImage simpleQrCode(String content, int height, int width) {
		return simpleQrCode(content, height, width, QrCodeConstant.ERR_LEVEL);
	}

	/**
	 * Simple qr code buffered image
	 *
	 * @param content content
	 * @param height  height
	 * @param width   width
	 * @param level   level
	 * @return the buffered image
	 * @throws IOException io exception
	 */
	public static BufferedImage simpleQrCode(String content, int height, int width, ErrorCorrectionLevel level) throws IOException {
		// 内容检查
		checkParam(content);

		// 参数格式化
		height = height <= 0 ? QrCodeConstant.QR_CODE_HEIGHT : height;
		width = width <= 0 ? QrCodeConstant.QR_CODE_WIDTH : width;
		level = Objects.isNull(level) ? ErrorCorrectionLevel.M : level;

		// 编码提示类型配置
		Map<EncodeHintType, Object> map = new HashMap<>(3);
		map.put(EncodeHintType.CHARACTER_SET, QrCodeConstant.FORMAT);
		map.put(EncodeHintType.ERROR_CORRECTION, level);
		map.put(EncodeHintType.MARGIN, QrCodeConstant.MARGIN);

		// 获取二维码位图矩阵
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, map);
		} catch (WriterException e) {
			log.error("获取二维码位图矩阵失败=[{}]", e.getMessage());
			throw new IOException("获取二维码位图矩阵失败");
		}

		// 获取二维码图像配置
		MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);

		// 获取二维码缓冲图像
		BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);

		return bufferedImage;
	}

	// -----------------------------------------------------------------------------------------------
	// BASE64 CODE
	// -----------------------------------------------------------------------------------------------

	/**
	 * Base 64 qr code string
	 *
	 * @param bufferedImage buffered image
	 * @return the string
	 * @throws IOException io exception
	 */
	public static String base64QrCode(BufferedImage bufferedImage) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String res = "";

		try {
			// 写入流中
			ImageIO.write(bufferedImage, "jpg", outputStream);
			// 转换成字节
			byte[] bytes = outputStream.toByteArray();
			// 对字节数组Base64编码  -- 转换成base64串
			String base64Img = new Base64().encodeToString(bytes);

			base64Img = base64Img.replaceAll("\n", "").replaceAll("\r", "");
			// 前面加 data:image/jpg;base64,
			res = "data:image/jpg;base64," + base64Img;
		} finally {
			// 关闭流
			if (outputStream != null) {
				outputStream.close();
			}
		}

		return res;
	}

	// -----------------------------------------------------------------------------------------------
	// Logo QR CODE
	// -----------------------------------------------------------------------------------------------

	/**
	 * Logo qr code buffered image
	 *
	 * @param qrCodeMatrixImage qr code matrix image
	 * @param logoFile          logo file
	 * @return the buffered image
	 * @throws IOException io exception
	 */
	public static BufferedImage logoQrCode(BufferedImage qrCodeMatrixImage, File logoFile) throws IOException {
		BufferedImage logoMatrixImage = ImageIO.read(logoFile);
		BufferedImage bufferedImage = logoQrCode(qrCodeMatrixImage, logoMatrixImage);
		return bufferedImage;
	}

	/**
	 * Logo qr code buffered image
	 *
	 * @param qrCodeMatrixImage     qr code matrix image
	 * @param byteArrayOutputStream byte array output stream
	 * @return the buffered image
	 * @throws IOException io exception
	 */
	public static BufferedImage logoQrCode(BufferedImage qrCodeMatrixImage, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
		InputStream inputStream = null;
		BufferedImage logoMatrixImage = null;
		try {
			inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			logoMatrixImage = ImageIO.read(inputStream);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		BufferedImage bufferedImage = logoQrCode(qrCodeMatrixImage, logoMatrixImage);
		return bufferedImage;
	}

	/**
	 * Logo qr code buffered image
	 *
	 * @param qrCodeMatrixImage qr code matrix image
	 * @param inputStream       input stream
	 * @return the buffered image
	 * @throws IOException io exception
	 */
	public static BufferedImage logoQrCode(BufferedImage qrCodeMatrixImage, InputStream inputStream) throws IOException {
		BufferedImage logoMatrixImage = null;
		try {
			logoMatrixImage = ImageIO.read(inputStream);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		BufferedImage bufferedImage = logoQrCode(qrCodeMatrixImage, logoMatrixImage);
		return bufferedImage;
	}

	/**
	 * Logo qr code buffered image
	 *
	 * @param qrCodeMatrixImage qr code matrix image
	 * @param imageInputStream  image input stream
	 * @return the buffered image
	 * @throws IOException io exception
	 */
	public static BufferedImage logoQrCode(BufferedImage qrCodeMatrixImage, ImageInputStream imageInputStream) throws IOException {
		BufferedImage logoMatrixImage = null;
		try {
			logoMatrixImage = ImageIO.read(imageInputStream);
		} finally {
			if (imageInputStream != null) {
				imageInputStream.close();
			}
		}
		BufferedImage bufferedImage = logoQrCode(qrCodeMatrixImage, logoMatrixImage);
		return bufferedImage;
	}

	/**
	 * Logo qr code buffered image
	 *
	 * @param qrCodeMatrixImage qr code matrix image
	 * @param url               url
	 * @return the buffered image
	 * @throws IOException io exception
	 */
	public static BufferedImage logoQrCode(BufferedImage qrCodeMatrixImage, URL url) throws IOException {
		BufferedImage logoMatrixImage = ImageIO.read(url);
		BufferedImage bufferedImage = logoQrCode(qrCodeMatrixImage, logoMatrixImage);
		return bufferedImage;
	}

	/**
	 * Logo qr code buffered image
	 *
	 * @param qrCodeMatrixImage qr code matrix image
	 * @param logoMatrixImage   logo matrix image
	 * @return the buffered image
	 */
	public static BufferedImage logoQrCode(BufferedImage qrCodeMatrixImage, BufferedImage logoMatrixImage) {
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
			BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			// 设置笔画对象
			imageGraphics.setStroke(stroke);
			// 指定弧度的圆角矩形
			RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width / 5 * 2 + 2, height / 5 * 2 + 2, width / 5 - 4, height / 5 - 4, 20, 20);
			imageGraphics.setColor(new Color(128, 128, 128));

			// 绘制圆弧矩形
			imageGraphics.draw(round2);
		} finally {
			// 释放资源Ω
			if (!Objects.isNull(imageGraphics)) {
				imageGraphics.dispose();
			}
			qrCodeMatrixImage.flush();
		}

		return qrCodeMatrixImage;
	}

	// -----------------------------------------------------------------------------------------------
	// 生成带文字的二维码
	// -----------------------------------------------------------------------------------------------

	/**
	 * Text qr code buffered image
	 *
	 * @param qrCode qr code
	 * @param font   font
	 * @param color  color
	 * @return the buffered image
	 */
	public static BufferedImage textQrCode(QrCode qrCode, Font font, Color color) {
		// handle font
		font = handleFont(qrCode.getTopText(), qrCode.getCenterText(), qrCode.getBottomText(), font);

		BufferedImage bufferedImage = simpleQrCode(qrCode.getContent());

		// 获取二维码的宽高
		int imageWidth = bufferedImage.getWidth();
		int imageHeight = bufferedImage.getHeight();

		Graphics graphics = null;
		try {
			graphics = bufferedImage.createGraphics();
			graphics.setFont(font);
			graphics.setColor(color);

			// 获取顶部字体的宽、高
			drawText(bufferedImage, font, qrCode.getTopText(), imageWidth, imageHeight, graphics, TextPositionEnum.TOP);
			drawText(bufferedImage, font, qrCode.getCenterText(), imageWidth, imageHeight, graphics, TextPositionEnum.CENTER);
			drawText(bufferedImage, font, qrCode.getBottomText(), imageWidth, imageHeight, graphics, TextPositionEnum.BOTTOM);
		} finally {
			if (graphics != null) {
				graphics.dispose();
			}
		}

		return bufferedImage;
	}

	// -----------------------------------------------------------------------------------------------
	// 批量生成二维码
	// -----------------------------------------------------------------------------------------------

	/**
	 * Batch text qr code list
	 *
	 * @param arrays arrays
	 * @return the list
	 * @throws Exception exception
	 */
	public static List<BatchQrCode> batchTextQrCode(List<QrCode> arrays) throws Exception {
		int size = arrays.size();
		List<BatchQrCode> list = new ArrayList<>(size);
		Font font = new Font("宋体", Font.ITALIC, 24);

		CountDownLatch countDownLatch = new CountDownLatch(size);

		long start = Instant.now().toEpochMilli();
		arrays.parallelStream().forEach(m -> {
			THREAD_POOL_EXECUTOR.execute(() -> {
				// gen qr code
				BufferedImage bufferedImage = simpleQrCode(m.getContent());

				// fill text
				bufferedImage = textQrCode(m, font, Color.BLACK);

				// add List
				list.add(new BatchQrCode(bufferedImage, m));

				countDownLatch.countDown();
			});
		});

		countDownLatch.await();
		long end = Instant.now().toEpochMilli();

		log.info("生成二维码共计耗时 {} ms", end - start);

		return list;
	}


	/**
	 * Batch logo qr code list
	 *
	 * @param arrays                arrays
	 * @param byteArrayOutputStream byte array output stream
	 * @return the list
	 * @throws Exception exception
	 */
	public static List<BatchQrCode> batchLogoQrCode(List<QrCode> arrays, ByteArrayOutputStream byteArrayOutputStream) throws Exception {
		// check byteArrayOutputStream
		if (byteArrayOutputStream == null) {
			throw new FileNotFoundException("ByteArrayOutputStream can not be null");
		}

		int size = arrays.size();
		List<BatchQrCode> list = new ArrayList<>(size);
		Font font = new Font("宋体", Font.ITALIC, 24);

		CountDownLatch countDownLatch = new CountDownLatch(size);

		long start = Instant.now().toEpochMilli();
		arrays.parallelStream().forEach(m -> {
			THREAD_POOL_EXECUTOR.execute(() -> {
				// gen qr code
				BufferedImage bufferedImage = simpleQrCode(m.getContent());

				// file picture
				try {
					bufferedImage = logoQrCode(bufferedImage, byteArrayOutputStream);
					// 默认处理：如果存在 logo 填充则不需要再增加中心文字
					m.setCenterText("");
				} catch (IOException e) {
					log.error("填充内容为 =[{}] 的 logo 发生错误=[{}]，提前终止！", m.getContent(), e.getMessage());
					return;
				}

				// fill text
				bufferedImage = textQrCode(m, font, Color.BLACK);

				// add List
				list.add(new BatchQrCode(bufferedImage, m));

				countDownLatch.countDown();
			});
		});

		countDownLatch.await();
		long end = Instant.now().toEpochMilli();

		log.info("生成二维码共计耗时 {} ms", end - start);

		return list;
	}


	/**
	 * Batch logo qr code list
	 *
	 * @param arrays   arrays
	 * @param logoFile logo file
	 * @return the list
	 * @throws Exception exception
	 */
	public static List<BatchQrCode> batchLogoQrCode(List<QrCode> arrays, File logoFile) throws Exception {
		// check file
		if (ObjectUtils.isEmpty(logoFile) || !logoFile.exists()) {
			throw new FileNotFoundException("Not fount this File");
		}

		int size = arrays.size();
		List<BatchQrCode> list = new ArrayList<>(size);
		Font font = new Font("宋体", Font.ITALIC, 24);

		CountDownLatch countDownLatch = new CountDownLatch(size);

		long start = Instant.now().toEpochMilli();
		arrays.parallelStream().forEach(m -> {
			THREAD_POOL_EXECUTOR.execute(() -> {
				// gen qr code
				BufferedImage bufferedImage = simpleQrCode(m.getContent());

				// file picture
				try {
					bufferedImage = logoQrCode(bufferedImage, logoFile);
					// 默认处理：如果存在 logo 填充则不需要再增加中心文字
					m.setCenterText("");
				} catch (IOException e) {
					log.error("填充内容为 =[{}] 的 logo 发生错误=[{}]，提前终止！", m.getContent(), e.getMessage());
					return;
				}

				// fill text
				bufferedImage = textQrCode(m, font, Color.BLACK);

				// add List
				list.add(new BatchQrCode(bufferedImage, m));

				countDownLatch.countDown();
			});
		});

		countDownLatch.await();
		long end = Instant.now().toEpochMilli();

		log.info("生成二维码共计耗时 {} ms", end - start);

		return list;
	}

	/**
	 * Batch logo qr code list
	 *
	 * @param arrays arrays
	 * @param url    url
	 * @return the list
	 * @throws Exception exception
	 */
	public static List<BatchQrCode> batchLogoQrCode(List<QrCode> arrays, URL url) throws Exception {
		// check URL
		try {
			url.openStream();
		} catch (MalformedURLException e) {
			throw new MalformedURLException("URL 格式错误");
		} catch (IOException e) {
			throw new IOException("无法读取 URL 链接");
		}

		int size = arrays.size();
		List<BatchQrCode> list = new ArrayList<>(size);
		Font font = new Font("宋体", Font.ITALIC, 24);

		CountDownLatch countDownLatch = new CountDownLatch(size);

		long start = Instant.now().toEpochMilli();

		final URL finalUrl = url;
		arrays.parallelStream().forEach(m -> {
			THREAD_POOL_EXECUTOR.execute(() -> {
				// gen qr code
				BufferedImage bufferedImage = simpleQrCode(m.getContent());

				// file text and picture
				try {
					bufferedImage = logoQrCode(bufferedImage, finalUrl);
					// 默认处理：如果存在 logo 填充则不需要再增加中心文字
					m.setCenterText("");
				} catch (IOException e) {
					log.error("填充内容为 =[{}] 的 logo 发生错误=[{}]，提前终止！", m.getContent(), e.getMessage());
					return;
				}

				// fill text
				bufferedImage = textQrCode(m, font, Color.BLACK);

				// add List
				list.add(new BatchQrCode(bufferedImage, m));

				countDownLatch.countDown();
			});
		});

		countDownLatch.await();
		long end = Instant.now().toEpochMilli();

		log.info("生成二维码共计耗时 {} ms", end - start);

		return list;
	}

	// -----------------------------------------------------------------------------------------------
	// WRITE BufferedImage TO File/OutputStream/ImageOutputStream
	// -----------------------------------------------------------------------------------------------

	/**
	 * Write 2 file *
	 *
	 * @param img      img
	 * @param fileType file type
	 * @param filepath filepath
	 * @throws IOException io exception
	 */
	public static void write2File(BufferedImage img, String fileType, String filepath) throws IOException {
		try {
			// 目录新建
			File file = new File(filepath);
			if (!file.isDirectory()) {
				throw new IOException(filepath + " is not a directory");
			}


			ImageIO.write(img, fileType, new File(filepath));
		} finally {
			// 释放资源
			if (img != null) {
				img.flush();
			}
		}
	}

	/**
	 * Write 2 stream *
	 *
	 * @param img          img
	 * @param fileType     file type
	 * @param outputStream output stream
	 * @throws IOException io exception
	 */
	public static void write2Stream(BufferedImage img, String fileType, OutputStream outputStream) throws IOException {
		try {
			ImageIO.write(img, fileType, outputStream);
		} finally {
			// 释放资源
			if (img != null) {
				img.flush();
			}
		}
	}

	/**
	 * Write 2 stream *
	 *
	 * @param img               img
	 * @param fileType          file type
	 * @param imageOutputStream image output stream
	 * @throws IOException io exception
	 */
	public static void write2Stream(BufferedImage img, String fileType, ImageOutputStream imageOutputStream) throws IOException {
		try {
			ImageIO.write(img, fileType, imageOutputStream);
		} finally {
			// 释放资源
			if (img != null) {
				img.flush();
			}
		}
	}

	// -----------------------------------------------------------------------------------------------
	// IDENTIFY QR CODE
	// -----------------------------------------------------------------------------------------------

	/**
	 * Identify qr code result
	 *
	 * @param url url
	 * @return the result
	 * @throws IOException       io exception
	 * @throws NotFoundException not found exception
	 */
	public static Result identifyQrCode(URL url) throws IOException, NotFoundException {
		BufferedImage bufferedImage = ImageIO.read(url);
		Result result = identifyQrCode(bufferedImage);
		return result;
	}

	/**
	 * Identify qr code result
	 *
	 * @param file file
	 * @return the result
	 * @throws IOException       io exception
	 * @throws NotFoundException not found exception
	 */
	public static Result identifyQrCode(File file) throws IOException, NotFoundException {
		BufferedImage bufferedImage = ImageIO.read(file);
		Result result = identifyQrCode(bufferedImage);
		return result;
	}

	/**
	 * Identify qr code result
	 *
	 * @param inputStream input stream
	 * @return the result
	 * @throws IOException       io exception
	 * @throws NotFoundException not found exception
	 */
	public static Result identifyQrCode(InputStream inputStream) throws IOException, NotFoundException {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(inputStream);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		Result result = identifyQrCode(bufferedImage);
		return result;
	}

	/**
	 * Identify qr code result
	 *
	 * @param imageInputStream image input stream
	 * @return the result
	 * @throws IOException       io exception
	 * @throws NotFoundException not found exception
	 */
	public static Result identifyQrCode(ImageInputStream imageInputStream) throws IOException, NotFoundException {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(imageInputStream);
		} finally {
			if (imageInputStream != null) {
				imageInputStream.close();
			}
		}
		Result result = identifyQrCode(bufferedImage);
		return result;
	}

	/**
	 * Identify qr code result
	 *
	 * @param bufferedImage buffered image
	 * @return the result
	 * @throws IOException       io exception
	 * @throws NotFoundException not found exception
	 */
	public static Result identifyQrCode(BufferedImage bufferedImage) throws IOException, NotFoundException {
		Result result = null;
		try {
			// 读取指定的二维码文件
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

			// 定义二维码参数
			Map hints = new HashMap<>(1);
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			result = new MultiFormatReader().decode(binaryBitmap, hints);

			// 输出相关的二维码信息
			log.info("解析二维码，二维码格式类型=[{}]、二维码内容=[{}]", result.getBarcodeFormat(), result.getText());
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
	 * Check param *
	 *
	 * @param obj obj
	 * @throws RuntimeException runtime exception
	 */
	private static void checkParam(Object obj) throws RuntimeException {
		if ((obj instanceof String) && (StringUtils.isEmpty(obj))) {
			throw new NullPointerException("param can not be null or empty");
		}
		if ((obj instanceof BufferedImage) && (obj == null)) {
			throw new NullPointerException("param can not be null");
		}
	}

	/**
	 * Draw text *
	 *
	 * @param bufferedImage buffered image
	 * @param font          font
	 * @param text          text
	 * @param imageWidth    image width
	 * @param imageHeight   image height
	 * @param graphics      graphics
	 * @param posEnum       pos enum
	 */
	private static void drawText(BufferedImage bufferedImage, Font font, String text, int imageWidth, int imageHeight, Graphics graphics, TextPositionEnum posEnum) {
		if (!StringUtils.isEmpty(text)) {
			text = new String(text.trim().getBytes(), Charset.forName("UTF-8"));
			FontMetrics metrics = FontDesignMetrics.getMetrics(font);
			int fontWidth = metrics.stringWidth(text);
			int fontHeight = metrics.getHeight();

			// 绘制图像
			fillText(bufferedImage, text, fontWidth, fontHeight, imageWidth, imageHeight, graphics, posEnum);
		}
	}

	/**
	 * Fill text *
	 *
	 * @param bufferedImage buffered image
	 * @param text          text
	 * @param fontWidth     font width
	 * @param fontHeight    font height
	 * @param imageWidth    image width
	 * @param imageHeight   image height
	 * @param graphics      graphics
	 * @param posEnum       pos enum
	 */
	private static void fillText(BufferedImage bufferedImage, String text,
								 int fontWidth, int fontHeight,
								 int imageWidth, int imageHeight,
								 Graphics graphics, TextPositionEnum posEnum) {
		int startX;
		int startY;
		Integer posEnumCode = posEnum.getCode();
		if (TextPositionEnum.TOP.getCode().equals(posEnumCode)) {
			startX = (imageWidth - fontWidth) / 2 < 0 ? 0 : (imageWidth - fontWidth) / 2;
			startY = fontHeight * 3 / 2;
			graphics.drawString(text, startX, startY);
		}
		if (TextPositionEnum.CENTER.getCode().equals(posEnumCode)) {
			startX = (imageWidth - fontWidth) / 2 + 5;
			startY = imageHeight / 2 + fontHeight / 2 - (fontHeight / 4) + 5;
			int endX = startX + fontWidth + 5;
			int endY = startY + 5;
			// 填充文字区域背景
			for (int x = 0; x < imageWidth; x++) {
				for (int y = 0; y < imageHeight; y++) {
					// 中心文字填充区域背景设置为空白
					if (x > (startX - 10) && x < endX && y > (startY - fontHeight) && y < endY) {
						bufferedImage.setRGB(x, y, QrCodeConstant.BACKGROUND_COLOR);
					}
				}
			}
			graphics.drawString(text, startX, startY);
		}
		if (TextPositionEnum.BOTTOM.getCode().equals(posEnumCode)) {
			startX = (imageWidth - fontWidth) / 2 < 0 ? 0 : (imageWidth - fontWidth) / 2;
			startY = imageHeight - fontHeight;
			graphics.drawString(text, startX, startY);
		}
	}

	/**
	 * Input stream 2 byte stream byte array output stream
	 *
	 * @param inputStream input stream
	 * @return the byte array output stream
	 * @throws IOException io exception
	 */
	private static ByteArrayOutputStream inputStream2ByteStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = inputStream.read(buffer)) > -1) {
			baos.write(buffer, 0, len);
		}
		return baos;
	}

	/**
	 * Handle font font
	 *
	 * @param topText    top text
	 * @param centerText center text
	 * @param bottomText bottom text
	 * @param font       font
	 * @return the font
	 */
	private static Font handleFont(String topText, String centerText, String bottomText, Font font) {
		int fontSize = font.getSize();

		// 不做处理
		if (topText.length() < NUM_8 && centerText.length() < NUM_8 && bottomText.length() < NUM_8 && fontSize < NUM_24) {
			return font;
		}

		// 如果字体过大
		if (fontSize > NUM_20) {
			font = new Font(font.getName(), font.getStyle(), NUM_20);
		}

		// 如果文本过长
		if (topText.length() > NUM_15 || centerText.length() > NUM_15 || bottomText.length() > NUM_15) {
			font = new Font(font.getName(), font.getStyle(), 16);
		}

		return font;
	}


}