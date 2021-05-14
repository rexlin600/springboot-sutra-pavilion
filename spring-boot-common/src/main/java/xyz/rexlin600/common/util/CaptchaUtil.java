package xyz.rexlin600.common.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 二维码工具类
 *
 * @author hekunlin
 * @since 2020 -08-07
 */
public class CaptchaUtil {

	/**
	 * Chars
	 */
	static char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

	/**
	 * 生成一个位数为count的随机验证码
	 *
	 * @param count count
	 * @return string
	 */
	public static String genCaptcha(int count) {
		StringBuilder captcha = new StringBuilder();

		for (int i = 0; i < count; i++) {
			//随机选取一个字母或数字
			char c = chars[ThreadLocalRandom.current().nextInt(chars.length)];
			captcha.append(c);
		}
		return captcha.toString();
	}

	/**
	 * 为一个验证码生成一个图片
	 * <p>
	 * 特性：
	 * - 颜色随机
	 * - 上下位置随机
	 * - 左右位置随机，但字符之间不会重叠
	 * - 左右随机旋转一个角度
	 * - 避免字符出界
	 * - 随机颜色的小字符做背景干扰
	 * - 根据字符大小自动调整图片大小、自动计算干扰字符的个数
	 *
	 * @param captcha captcha
	 * @return buffered image
	 * @author XuJijun
	 */
	@SuppressWarnings("AlibabaRemoveCommentedCode")
	public static BufferedImage genCaptchaImg(String captcha) {
		ThreadLocalRandom r = ThreadLocalRandom.current();

		int count = captcha.length();
		// code的字体大小
		int fontSize = 80;
		// 字符间隔
		int fontMargin = fontSize / 4;
		// 图片长度
		int width = (fontSize + fontMargin) * count + fontMargin;
		// 图片高度，根据字体大小自动调整；调整这个系数可以调整字体占图片的比例
		int height = (int) (fontSize * 1.2);
		// 字符平均占位宽度
		int avgWidth = width / count;
		// 最大旋转度数
		int maxDegree = 26;

		// 背景颜色
		Color bkColor = Color.WHITE;
		// 验证码的颜色
		Color[] catchaColor = {Color.MAGENTA, Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE, Color.PINK};

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();

		// 填充底色为灰白
		g.setColor(bkColor);
		g.fillRect(0, 0, width, height);

		// 画边框
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, width - 1, height - 1);

		// 画干扰字母、数字

		// 调整分母大小以调整干扰字符大小
		int dSize = fontSize / 3;
		Font font = new Font("Fixedsys", Font.PLAIN, dSize);
		g.setFont(font);
		// 根据面积计算干扰字母的个数
		int dNumber = width * height / dSize / dSize;
		for (int i = 0; i < dNumber; i++) {
			char dCode = chars[r.nextInt(chars.length)];
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.drawString(String.valueOf(dCode), r.nextInt(width), r.nextInt(height));
		}

		// 开始画验证码：

		// 创建字体
		font = new Font(Font.MONOSPACED, Font.ITALIC | Font.BOLD, fontSize);
		// 设置字体
		g.setFont(font);

		for (int i = 0; i < count; i++) {
			char c = captcha.charAt(i);
			//随机选取一种颜色
			g.setColor(catchaColor[r.nextInt(catchaColor.length)]);

			//随机旋转一个角度[-maxDegre, maxDegree]
			int degree = r.nextInt(-maxDegree, maxDegree + 1);

			//偏移系数，和旋转角度成反比，以避免字符在图片中越出边框

			//加上1，避免出现结果为0
			double offsetFactor = 1 - (Math.abs(degree) / (maxDegree + 1.0));

			//旋转一个角度
			g.rotate(degree * Math.PI / 180);
			//横向偏移的距离
			int x = (int) (fontMargin + r.nextInt(avgWidth - fontSize) * offsetFactor);
			//上下偏移的距离
			int y = (int) (fontSize + r.nextInt(height - fontSize) * offsetFactor);

			//x,y是字符的左下角，偏离原点的距离！！！
			g.drawString(String.valueOf(c), x, y);

			//画完一个字符之后，旋转回原来的角度
			g.rotate(-degree * Math.PI / 180);
			//移动到下一个画画的原点
			g.translate(avgWidth, 0);

			//X、Y坐标在合适的范围内随机，不旋转：
			//g.drawString(String.valueOf(c), width/count*i+r.nextInt(width/count-fontSize), fontSize+r.nextInt(height-fontSize));
		}

		g.dispose();

		return image;
	}
}
