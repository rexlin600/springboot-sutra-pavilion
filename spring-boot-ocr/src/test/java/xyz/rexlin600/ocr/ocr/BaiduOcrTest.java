package xyz.rexlin600.ocr.ocr;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import xyz.rexlin600.ocr.ocr.core.OcrCreditCode;
import xyz.rexlin600.ocr.ocr.core.OcrFileUtil;

import java.io.File;

/**
 * 百度OCR识别测试
 *
 * @auther hekunlin
 * @since 2020 -08-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaiduOcrTest {

	/**
	 * Baidu ocr
	 */
	private final BaiduOcr baiduOcr;

	/**
	 * Baidu ocr test
	 *
	 * @param baiduOcr baidu ocr
	 */
	@Autowired
	public BaiduOcrTest(BaiduOcr baiduOcr) {
		this.baiduOcr = baiduOcr;
	}


	/**
	 * Test recognition business license
	 */
	@SneakyThrows
	@Test
	public void testRecognitionBusinessLicense1() {
		String filePath = "E:\\Github_Workspace\\repo\\springboot-sutra-pavilion\\spring-boot-ocr\\src\\main\\resources\\images\\license1.png";
		OcrCreditCode res = baiduOcr.ocrRecognition(filePath);
		System.out.println(res);
	}

	/**
	 * Test recognition business license
	 */
	@SneakyThrows
	@Test
	public void testRecognitionBusinessLicense2() {
		String filePath = "E:\\Github_Workspace\\repo\\springboot-sutra-pavilion\\spring-boot-ocr\\src\\main\\resources\\images\\license2.png";
		byte[] bytes = OcrFileUtil.readFileByBytes(filePath);
		OcrCreditCode res = baiduOcr.ocrRecognition(bytes);
		System.out.println(res);
	}

	/**
	 * Test recognition business license
	 */
	@SneakyThrows
	@Test
	public void testRecognitionBusinessLicense3() {
		String filePath = "E:\\Github_Workspace\\repo\\springboot-sutra-pavilion\\spring-boot-ocr\\src\\main\\resources\\images\\license2.png";
		File file = new File(filePath);
		MultipartFile multipartFile = OcrFileUtil.buildMultipartFile(file);

		byte[] bytes = OcrFileUtil.readFileByBytes(multipartFile);
		OcrCreditCode res = baiduOcr.ocrRecognition(bytes);
		System.out.println(res);
	}

}