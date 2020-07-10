package xyz.rexlin600.custom.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Goods
 *
 * @author hekunlin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("goods")
public class Goods extends Model {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

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
	 * Update date
	 */
	private LocalDateTime updateDate;


}
