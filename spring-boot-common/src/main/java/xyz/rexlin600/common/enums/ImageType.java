package xyz.rexlin600.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Image type
 *
 * @author hekunlin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ImageType {

	/**
	 * Gif image type
	 */
	GIF(1, "GIF"),
	/**
	 * Jpg image type
	 */
	JPG(2, "JPG"),
	/**
	 * Png image type
	 */
	PNG(3, "PNG"),
	/**
	 * Jpeg image type
	 */
	JPEG(4, "JPEG"),
	/**
	 * Bmp image type
	 */
	BMP(5, "BMP"),
	/**
	 * Tiff image type
	 */
	TIFF(6, "TIFF"),
	/**
	 * Eps image type
	 */
	EPS(7, "EPS"),
	/**
	 * Emf image type
	 */
	EMF(8, "EMF"),
	/**
	 * Wmf image type
	 */
	WMF(9, "WMF");

	/**
	 * Code
	 */
	private Integer code;

	/**
	 * Name
	 */
	private String name;

}