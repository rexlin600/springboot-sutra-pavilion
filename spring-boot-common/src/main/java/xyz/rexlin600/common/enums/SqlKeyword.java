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
     * AND
     */
    AND("AND"),
    /**
     * OR
     */
    OR("OR"),
    /**
     * IN
     */
    IN("IN"),
    /**
     * NOT
     */
    NOT("NOT"),
    /**
     * LIKE
     */
    LIKE("LIKE"),
    /**
     * =
     */
    EQ("="),
    /**
     * <>
     */
    NE("<>"),
    /**
     * >
     */
    GT(">"),
    /**
     * >=
     */
    GE(">="),
    /**
     * <
     */
    LT("<"),
    /**
     * <=
     */
    LE("<="),
    /**
     * IS NULL
     */
    IS_NULL("IS NULL"),
    /**
     * IS NOT NULL
     */
    IS_NOT_NULL("IS NOT NULL"),
    /**
     * GROUP BY
     */
    GROUP_BY("GROUP BY"),
    /**
     * HAVING
     */
    HAVING("HAVING"),
    /**
     * ORDER BY
     */
    ORDER_BY("ORDER BY"),
    /**
     * EXISTS
     */
    EXISTS("EXISTS"),
    /**
     * BETWEEN
     */
    BETWEEN("BETWEEN"),
    /**
     * ASC
     */
    ASC("ASC"),
    /**
     * DESC
     */
    DESC("DESC");

    /**
     * 数据值
     */
    private String keyword;

    SqlKeyword(String keyword) {
        this.keyword = keyword;
    }


}
