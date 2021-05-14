package xyz.rexlin600.ocr.ocr;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.ocr.ocr.core.BaiduOcrConfigBean;
import xyz.rexlin600.ocr.ocr.core.OcrConst;
import xyz.rexlin600.ocr.ocr.core.OcrCreditCode;

import java.io.IOException;
import java.util.HashMap;

/**
 * 百度 OCR 工具类
 *
 * @author hekunlin
 * @since 2020 /8/13
 */
@Slf4j
@Data
@Component
public class BaiduOcr {

	/**
	 * AipOcr instance
	 */
	private static AipOcr instance = null;
	/**
	 * Ocr config bean
	 */
	private final BaiduOcrConfigBean ocrConfigBean;

	/**
	 * Baidu ocr
	 *
	 * @param ocrConfigBean ocr config bean
	 */
	@Autowired
	public BaiduOcr(BaiduOcrConfigBean ocrConfigBean) {
		this.ocrConfigBean = ocrConfigBean;
		init();
	}

	/**
	 * OCR 初始化
	 */
	public void init() {
		log.info("==>  初始化 OCR instance 开始 ...");
		if (instance == null) {
			synchronized (BaiduOcr.class) {
				if (instance == null) {
					instance = new AipOcr(
							ocrConfigBean.getAppId(),
							ocrConfigBean.getAppKey(),
							ocrConfigBean.getAppSecretKey());

					instance.setConnectionTimeoutInMillis(2000);
					instance.setSocketTimeoutInMillis(60000);
					log.info("==>  初始化 OCR instance 完成 ...");
				}
			}
		}
	}

	/**
	 * Ocr recognition
	 *
	 * @param filePath filePath
	 * @return the baiduOcrRes object
	 * @throws IOException         io exception
	 * @throws FileUploadException file upload exception
	 */
	public OcrCreditCode ocrRecognition(String filePath) throws IOException, FileUploadException {
		byte[] data = new byte[0];
		try {
			data = Util.readFileByBytes(filePath);
		} catch (IOException e) {
			log.error("==>  读取图片失败：{}", e.getMessage());
			throw new IOException("读取图片失败");
		}
		return ocrRecognition(data);
	}

	/**
	 * Ocr recognition
	 *
	 * @param data data
	 * @return the baiduOcrRes object
	 * @throws FileUploadException file upload exception
	 */
	public OcrCreditCode ocrRecognition(byte[] data) throws FileUploadException {
		HashMap<String, String> options = new HashMap<>(4);
		options.put(OcrConst.LANGUAGE_TYPE, "CHN_ENG");
		options.put(OcrConst.DETECT_DIRECTION, "true");
		options.put(OcrConst.DETECT_LANGUAGE, "false");
		options.put(OcrConst.PROBABILITY, "false");
		// OCR 识别：会自定判定是否需要重新获取 Token
		JSONObject jsonObject = instance.businessLicense(data, options);

		// 如果存在错误信息
		if (jsonObject.has(OcrConst.ERROR_CODE) && jsonObject.has(OcrConst.ERROR_MESSAGE)) {
			log.info("==> 营业执照OCR识别错误,错误码：{},错误原因：{}", jsonObject.get(OcrConst.ERROR_CODE), jsonObject.get(OcrConst.ERROR_MESSAGE));
			throw new FileUploadException("营业执照OCR识别错误,错误码：" + jsonObject.get(OcrConst.ERROR_CODE) + " 错误原因：" + jsonObject.get(OcrConst.ERROR_MESSAGE));
		}

		// convert object
		JSONObject words = (JSONObject) jsonObject.get(OcrConst.WORDS_RESULT);

		return OcrCreditCode.builder()
				.logId(jsonObject.getLong(OcrConst.LOG_ID))
				.wordsResultNum(jsonObject.getLong(OcrConst.WORDS_RESULT_NUM))
				.direction(jsonObject.getLong(OcrConst.DIRECTION))
				.creditCode((String) ((JSONObject) words.get("社会信用代码")).get(OcrConst.WORDS))
				.formation((String) ((JSONObject) words.get("组成形式")).get(OcrConst.WORDS))
				.enterpriseBusinessScope((String) ((JSONObject) words.get("经营范围")).get(OcrConst.WORDS))
				.legalPerson((String) ((JSONObject) words.get("法人")).get(OcrConst.WORDS))
				.establishment((String) ((JSONObject) words.get("成立日期")).get(OcrConst.WORDS))
				.enterpriseRegisteredCapital((String) ((JSONObject) words.get("注册资本")).get(OcrConst.WORDS))
				.certificateNumber((String) ((JSONObject) words.get("证件编号")).get(OcrConst.WORDS))
				.address((String) ((JSONObject) words.get("地址")).get(OcrConst.WORDS))
				.accountName((String) ((JSONObject) words.get("单位名称")).get(OcrConst.WORDS))
				.enterpriseType((String) ((JSONObject) words.get("类型")).get(OcrConst.WORDS))
				.creditCodeValid((String) ((JSONObject) words.get("有效期")).get(OcrConst.WORDS))
				.build();
	}

}