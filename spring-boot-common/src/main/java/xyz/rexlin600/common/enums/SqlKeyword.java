package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 数据库关键字枚举类
 *
 * @author hekunlin
 */
@NoArgsConstructor
@Getter
public enum SqlKeyword {

    /**
     * 符号 AND
     */
    AND("AND"),
    /**
     * 符号 OR
     */
    OR("OR"),
    /**
     * 符号 IN
     */
    IN("IN"),
    /**
     * 符号 NOT
     */
    NOT("NOT"),
    /**
     * 符号 LIKE
     */
    LIKE("LIKE"),
    /**
     * 符号 等于
     */
    EQ("="),
    /**
     * 符号 不等于
     */
    NE("<>"),
    /**
     * 符号 大于
     */
    GT("&gt;"),
    /**
     * 符号 大于等于
     */
    GE("&gt;="),
    /**
     * 符号 小于
     */
    LT("&lt;"),
    /**
     * 符号 小于等于
     */
    LE("&lt;="),
    /**
     * 符号 IS NULL
     */
    IS_NULL("IS NULL"),
    /**
     * 符号 IS NOT NULL
     */
    IS_NOT_NULL("IS NOT NULL"),
    /**
     * 符号 GROUP BY
     */
    GROUP_BY("GROUP BY"),
    /**
     * 符号 HAVING
     */
    HAVING("HAVING"),
    /**
     * 符号 ORDER BY
     */
    ORDER_BY("ORDER BY"),
    /**
     * 符号 EXISTS
     */
    EXISTS("EXISTS"),
    /**
     * 符号 BETWEEN
     */
    BETWEEN("BETWEEN"),
    /**
     * 符号 ASC
     */
    ASC("ASC"),
    /**
     * 符号 DESC
     */
    DESC("DESC");

    /**
     * 关键字
     */
    private String keyword;

    SqlKeyword(String keyword) {
        this.keyword = keyword;
    }


}
