package xyz.rexlin600.qrcode.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Text position enum
 *
 * @author hekunlin
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TextPositionEnum {

	/**
	 * Top text position enum
	 */
	TOP(1, "TOP"),
	/**
	 * Center text position enum
	 */
	CENTER(2, "CENTER"),
	/**
	 * Bottom text position enum
	 */
	BOTTOM(3, "BOTTOM");

	/**
	 * Code
	 */
	private Integer code;
	/**
	 * Position
	 */
	private String position;

}