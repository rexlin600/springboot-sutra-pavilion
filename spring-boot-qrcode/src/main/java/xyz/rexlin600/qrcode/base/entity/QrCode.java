package xyz.rexlin600.qrcode.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Qr code
 *
 * @author hekunlin
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCode implements Serializable {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Content
	 */
	private String content;

	/**
	 * Top text
	 */
	private String topText;

	/**
	 * Center text
	 */
	private String centerText;

	/**
	 * Bottom text
	 */
	private String bottomText;

}