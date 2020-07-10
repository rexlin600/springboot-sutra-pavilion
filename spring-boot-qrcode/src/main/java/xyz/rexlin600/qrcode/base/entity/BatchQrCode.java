package xyz.rexlin600.qrcode.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * Batch qr code
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchQrCode {

	/**
	 * Buffered image
	 */
	private BufferedImage bufferedImage;

	/**
	 * Qr code
	 */
	private QrCode qrCode;

}