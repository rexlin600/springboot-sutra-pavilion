package xyz.rexlin600.java8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Goods
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods {

	/**
	 * Id
	 */
	private Long id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Sort
	 */
	private Integer sort;
	/**
	 * Position
	 */
	private String position;
	/**
	 * Weight
	 */
	private Double weight;
	/**
	 * Color
	 */
	private String color;
	/**
	 * Create date
	 */
	private LocalDateTime createDate;
	/**
	 * Update time
	 */
	private LocalDateTime updateTime;
}