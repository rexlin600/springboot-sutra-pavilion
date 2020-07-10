package xyz.rexlin600.mybatisplus.codegen.entity;

import lombok.*;

/**
 * Table meta data
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableMetaData {

	/**
	 * Table name
	 */
	private String tableName;

	/**
	 * Table comment
	 */
	private String tableComment;

	/**
	 * Engine
	 */
	private String engine;

	/**
	 * Table collation
	 */
	private String tableCollation;

	/**
	 * Create time
	 */
	private String createTime;

	/**
	 * Update time
	 */
	private String updateTime;

}