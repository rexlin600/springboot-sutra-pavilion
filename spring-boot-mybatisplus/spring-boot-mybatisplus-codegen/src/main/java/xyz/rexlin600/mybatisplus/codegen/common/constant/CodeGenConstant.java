package xyz.rexlin600.mybatisplus.codegen.common.constant;

/**
 * CodeGenConstant 常量类
 *
 * @author: hekunlin
 * @since: 2020/1/14
 */
public class CodeGenConstant {

    /**
     * 数据库驱动
     */
    public static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final String JDBC_DRIVER_CJ_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static final String BASE_QUERY_TABLE_SQL = "select table_name, table_comment, engine, table_collation, create_time, update_time " +
            "from information_schema.tables " +
            "where table_schema = (select database()) order by create_time desc ";
    public static final String COUNT_QUERY_TABLE_SQL = "select count(table_name) as total " +
            "from information_schema.tables " +
            "where table_schema = (select database())";
    public static final String LIMIT = "limit ";

    /**
     * AES 规定密钥长度为16位
     */
    public static final String CONTENT = "springboot-sutra";

    /**
     * vm 模板
     */
    public static final String VELOCITY = "/templates/mapper.xml.vm";

    /**
     * Linux 系统时默认生成代码的路径
     */
    public static final String LINUX_PATH = "/opt";

    /**
     * SUCCESS
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * FAILED
     */
    public static final String FAILED = "FAILED";


}