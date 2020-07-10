package xyz.rexlin600.starter.dds.util;

/**
 * Code gen constant
 *
 * @author hekunlin
 */
public class CodeGenConstant {

	/**
	 * JDBC_DRIVER_CLASS_NAME
	 */
	public static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	/**
	 * JDBC_DRIVER_CJ_CLASS_NAME
	 */
	public static final String JDBC_DRIVER_CJ_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

	/**
	 * BASE_QUERY_TABLE_SQL
	 */
	public static final String BASE_QUERY_TABLE_SQL = "select table_name, table_comment, engine, table_collation, create_time, update_time " +
			"from information_schema.tables " +
			"where table_schema = (select database()) order by create_time desc ";
	/**
	 * COUNT_QUERY_TABLE_SQL
	 */
	public static final String COUNT_QUERY_TABLE_SQL = "select count(table_name) as total " +
			"from information_schema.tables " +
			"where table_schema = (select database())";
	/**
	 * LIMIT
	 */
	public static final String LIMIT = "limit ";

	/**
	 * AES 规定密钥长度为16位
	 */


}