package xyz.rexlin600.flyway.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Tb flyway
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbFlyway implements Serializable {

	/**
	 * Id
	 */
	private Long id;

	/**
	 * Name
	 */
	private String name;

	/**
	 * Length
	 */
	private Double length;

	/**
	 * Create time
	 */
	private Date createTime;

}