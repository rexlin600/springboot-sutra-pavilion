package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Db type
 *
 * @author hekunlin
 */
@SuppressWarnings("SpellCheckingInspection")
@NoArgsConstructor
@Getter
public enum DbType {

	/**
	 * Mysql db type
	 */
	MYSQL("mysql", "MySql数据库"),
	/**
	 * Mariadb db type
	 */
	MARIADB("mariadb", "MariaDB数据库"),
	/**
	 * Oracle db type
	 */
	ORACLE("oracle", "Oracle11g及以下数据库(高版本推荐使用ORACLE_NEW)"),
	/**
	 * Oracle 12 c db type
	 */
	ORACLE_12C("oracle12c", "Oracle12c+数据库"),
	/**
	 * Db 2 db type
	 */
	DB2("db2", "DB2数据库"),
	/**
	 * H 2 db type
	 */
	H2("h2", "H2数据库"),
	/**
	 * Hsql db type
	 */
	HSQL("hsql", "HSQL数据库"),
	/**
	 * Sqlite db type
	 */
	SQLITE("sqlite", "SQLite数据库"),
	/**
	 * Postgre sql db type
	 */
	POSTGRE_SQL("postgresql", "Postgre数据库"),
	/**
	 * Sql server 2005 db type
	 */
	SQL_SERVER2005("sqlserver2005", "SQLServer2005数据库"),
	/**
	 * Sql server db type
	 */
	SQL_SERVER("sqlserver", "SQLServer数据库"),
	/**
	 * Dm db type
	 */
	DM("dm", "达梦数据库"),
	/**
	 * Xu gu db type
	 */
	XU_GU("xugu", "虚谷数据库"),
	/**
	 * Kingbase es db type
	 */
	KINGBASE_ES("kingbasees", "人大金仓数据库"),
	/**
	 * Other db type
	 */
	OTHER("other", "其他数据库");

	/**
	 * DB_CACHE_MAP
	 */
	private static Map<String, DbType> DB_CACHE_MAP = new ConcurrentHashMap();

	static {
		DbType[] var0 = values();
		int var1 = var0.length;

		for (int var2 = 0; var2 < var1; ++var2) {
			DbType dbType = var0[var2];
			DB_CACHE_MAP.put(dbType.getDb().toLowerCase(), dbType);
		}
	}

	/**
	 * Db
	 */
	private String db;
	/**
	 * Desc
	 */
	private String desc;

	/**
	 * Db type
	 *
	 * @param db   db
	 * @param desc desc
	 */
	DbType(final String db, final String desc) {
		this.db = db;
		this.desc = desc;
	}

	/**
	 * Gets db type *
	 *
	 * @param dbType db type
	 * @return the db type
	 */
	public static DbType getDbType(String dbType) {
		return (DbType) DB_CACHE_MAP.getOrDefault(dbType.toLowerCase(), OTHER);
	}

}
