package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库枚举类型
 *
 * @author hekunlin
 */

@SuppressWarnings("SpellCheckingInspection")
@NoArgsConstructor
@Getter
public enum DbType {

    /**
     * 各种数据库类型枚举类
     */
    MYSQL("mysql", "MySql数据库"),
    MARIADB("mariadb", "MariaDB数据库"),
    ORACLE("oracle", "Oracle11g及以下数据库(高版本推荐使用ORACLE_NEW)"),
    ORACLE_12C("oracle12c", "Oracle12c+数据库"),
    DB2("db2", "DB2数据库"),
    H2("h2", "H2数据库"),
    HSQL("hsql", "HSQL数据库"),
    SQLITE("sqlite", "SQLite数据库"),
    POSTGRE_SQL("postgresql", "Postgre数据库"),
    SQL_SERVER2005("sqlserver2005", "SQLServer2005数据库"),
    SQL_SERVER("sqlserver", "SQLServer数据库"),
    DM("dm", "达梦数据库"),
    XU_GU("xugu", "虚谷数据库"),
    KINGBASE_ES("kingbasees", "人大金仓数据库"),
    OTHER("other", "其他数据库");

    private String db;
    private String desc;
    private static Map<String, DbType> DB_CACHE_MAP = new ConcurrentHashMap();

    /**
     * DbType 构造器
     *
     * @param db   数据库名称
     * @param desc 数据库描述
     */
    DbType(final String db, final String desc) {
        this.db = db;
        this.desc = desc;
    }

    /**
     * 获取数据库类型
     *
     * @param dbType 数据库类型
     * @return {@link DbType}
     */
    public static DbType getDbType(String dbType) {
        return (DbType) DB_CACHE_MAP.getOrDefault(dbType.toLowerCase(), OTHER);
    }

    static {
        DbType[] var0 = values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            DbType dbType = var0[var2];
            DB_CACHE_MAP.put(dbType.getDb().toLowerCase(), dbType);
        }
    }

}
