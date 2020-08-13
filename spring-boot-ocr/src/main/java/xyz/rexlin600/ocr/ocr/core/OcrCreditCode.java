package xyz.rexlin600.ocr.ocr.core;

import lombok.*;

import java.io.Serializable;

/**
 * 百度OCR识别营业执照返回类
 *
 * @author hekunlin
 * @since 2020 -08-13
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OcrCreditCode implements Serializable {

	/**
	 * logId
	 */
	private Long logId;

	/**
	 * words 数量
	 */
	private Long wordsResultNum;

	/**
	 * direction
	 */
	private Long direction;

	/**
	 * 社会信用代码
	 */
	private String creditCode;

	/**
	 * 组成形式
	 */
	private String formation;

	/**
	 * 经营范围
	 */
	private String enterpriseBusinessScope;

	/**
	 * 法人
	 */
	private String legalPerson;

	/**
	 * 成立日期
	 */
	private String establishment;

	/**
	 * 注册资本
	 */
	private String enterpriseRegisteredCapital;

	/**
	 * 证件编号
	 */
	private String certificateNumber;

	/**
	 * 企业地址
	 */
	private String address;

	/**
	 * 单位名称
	 */
	private String accountName;

	/**
	 * 企业类型
	 */
	private String enterpriseType;

	/**
	 * 有效期
	 */
	private String creditCodeValid;

}