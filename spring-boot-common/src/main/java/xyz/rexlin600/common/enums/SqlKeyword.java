package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Sql keyword
 *
 * @author hekunlin
 */
@NoArgsConstructor
@Getter
public enum SqlKeyword {

	/**
	 * And sql keyword
	 */
	AND("AND"),
	/**
	 * Or sql keyword
	 */
	OR("OR"),
	/**
	 * In sql keyword
	 */
	IN("IN"),
	/**
	 * Not sql keyword
	 */
	NOT("NOT"),
	/**
	 * Like sql keyword
	 */
	LIKE("LIKE"),
	/**
	 * Eq sql keyword
	 */
	EQ("="),
	/**
	 * Ne sql keyword
	 */
	NE("<>"),
	/**
	 * Gt sql keyword
	 */
	GT("&gt;"),
	/**
	 * Ge sql keyword
	 */
	GE("&gt;="),
	/**
	 * Lt sql keyword
	 */
	LT("&lt;"),
	/**
	 * Le sql keyword
	 */
	LE("&lt;="),
	/**
	 * Is null
	 */
	IS_NULL("IS NULL"),
	/**
	 * Is not null
	 */
	IS_NOT_NULL("IS NOT NULL"),
	/**
	 * Group by
	 */
	GROUP_BY("GROUP BY"),
	/**
	 * Having sql keyword
	 */
	HAVING("HAVING"),
	/**
	 * Order by
	 */
	ORDER_BY("ORDER BY"),
	/**
	 * Exists sql keyword
	 */
	EXISTS("EXISTS"),
	/**
	 * Between sql keyword
	 */
	BETWEEN("BETWEEN"),
	/**
	 * Asc sql keyword
	 */
	ASC("ASC"),
	/**
	 * Desc sql keyword
	 */
	DESC("DESC");

	/**
	 * Keyword
	 */
	private String keyword;

	/**
	 * Sql keyword
	 *
	 * @param keyword keyword
	 */
	SqlKeyword(String keyword) {
		this.keyword = keyword;
	}


}
