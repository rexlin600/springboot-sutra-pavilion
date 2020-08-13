package xyz.rexlin600.ocr.rest;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.ocr.ocr.BaiduOcr;
import xyz.rexlin600.ocr.ocr.core.OcrCreditCode;
import xyz.rexlin600.ocr.ocr.core.OcrFileUtil;

/**
 * Ocr 测试 Rest
 *
 * @author hekunlin
 * @since 2020 /8/13
 */
@RestController
@RequestMapping("/ocr")
public class OcrTestRest {

	/**
	 * Baidu ocr
	 */
	private final BaiduOcr baiduOcr;

	/**
	 * Ocr test rest
	 *
	 * @param baiduOcr baidu ocr
	 */
	@Autowired
	public OcrTestRest(BaiduOcr baiduOcr) {
		this.baiduOcr = baiduOcr;
	}

	/**
	 * Ocr string
	 *
	 * @return the string
	 */
	@SneakyThrows
	@GetMapping("/test")
	public String ocr() {
		String filePath = "E:\\Github_Workspace\\repo\\springboot-sutra-pavilion\\spring-boot-ocr\\src\\main\\resources\\images\\license2.png";
		byte[] bytes = OcrFileUtil.readFileByBytes(filePath);
		OcrCreditCode res = baiduOcr.ocrRecognition(bytes);
		return res.toString();
	}

}