package xyz.rexlin600.starter.dds.util;

/**
 * @author lengleng
 * @date 2019-04-01
 * <p>
 * 数据源相关常量
 */
public interface DataSourceConstants {
    /**
     * 查询数据源的SQL
     */
    String QUERY_DS_SQL = "select * from data_source";

    /**
     * 动态路由KEY
     */
    String DS_ROUTE_KEY = "id";

    /**
     * 数据源名称
     */
    String DS_NAME = "db_name";

    /**
     * jdbcurl
     */
    String DS_JDBC_URL = "url";

    /**
     * 用户名
     */
    String DS_USER_NAME = "username";

    /**
     * 密码
     */
    String DS_USER_PWD = "password";

    /**
     * AES密钥
     */
    String CONTENT = "springboot-sutra";

}
