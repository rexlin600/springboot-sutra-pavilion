package xyz.rexlin600.rabbitmq.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Invoke type enum
 *
 * @author hekunlin
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum InvokeTypeEnum {

	/**
	 * Default invoke type enum
	 */
	DEFAULT("default", 0),
	/**
	 * Direct invoke type enum
	 */
	DIRECT("direct", 1),
	/**
	 * Fanout invoke type enum
	 */
	FANOUT("fanout", 2),
	/**
	 * Header invoke type enum
	 */
	HEADER("header", 3),
	/**
	 * Topic invoke type enum
	 */
	TOPIC("config", 4),
	/**
	 * Simple invoke type enum
	 */
	SIMPLE("simple", 5),
	/**
	 * Work invoke type enum
	 */
	WORK("work", 6),
	/**
	 * Dl invoke type enum
	 */
	DL("dl", 7),
	/**
	 * Custom invoke type enum
	 */
	CUSTOM("custom", 8);


	/**
	 * Type
	 */
	private String type;
	/**
	 * Code
	 */
	private Integer code;

}