package xyz.rexlin600.starter.dds.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import xyz.rexlin600.starter.dds.util.AesUtils;
import xyz.rexlin600.starter.dds.util.CodeGenConstant;
import xyz.rexlin600.starter.dds.util.DataSourceConstants;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: rexlin600
 * @date: 2020-03-07
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicDataSourceConfig implements TransactionManagementConfigurer {

    /**
     * 保存数据源Map、HikariCp数据源
     */
    private final Map<Object, Object> dataSourceMap = new HashMap<>(8);
    private final HikariCpDataSourceProperties hikariCpDataSourceProperties;


    /**
     * 动态数据源
     *
     * @return
     */
    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        // HikariDataSource
        HikariDataSource hikariDataSource = getHikariDataSource();

        // default DataSource
        dynamicDataSource.setDefaultTargetDataSource(hikariDataSource);

        // save in ThreadLocal
        dataSourceMap.put(0, hikariDataSource);

        // set Target DataSources
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        return dynamicDataSource;
    }

    /**
     * 组装默认配置的数据源，查询数据库配置
     */
    @PostConstruct
    public void init() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(hikariCpDataSourceProperties.getUrl());
        driverManagerDataSource.setDriverClassName(hikariCpDataSourceProperties.getDriverClassName());
        driverManagerDataSource.setUsername(hikariCpDataSourceProperties.getUsername());
        driverManagerDataSource.setPassword(hikariCpDataSourceProperties.getPassword());

        // 查询所有数据源
        List<Map<String, Object>> dbList = new JdbcTemplate(driverManagerDataSource).queryForList(DataSourceConstants.QUERY_DS_SQL);
        log.info("==>  开始：初始化动态数据源");
        Optional.of(dbList).ifPresent(list -> list.forEach(db -> {
            log.info("==>  加载到数据源:{}", db.get(DataSourceConstants.DS_NAME));

            // HikariConfig、HikariDataSource
            HikariConfig config = new HikariConfig();
            String url = "jdbc:mysql://" + db.get("host").toString().trim() + ":" + db.get("port").toString().trim() + "/"
                    + db.get("db_name").toString().trim() + "?characterEncoding=utf8&serverTimezone=GMT%2B8";
            config.setJdbcUrl(url);
            config.setDriverClassName(CodeGenConstant.JDBC_DRIVER_CJ_CLASS_NAME);
            config.setUsername(AesUtils.decrypt(db.get("username").toString().trim()));
            config.setPassword(AesUtils.decrypt(db.get("password").toString().trim()));
            injectHikariConfig(config);
            HikariDataSource hikariDataSource = new HikariDataSource(config);

            dataSourceMap.put(db.get(DataSourceConstants.DS_ROUTE_KEY), hikariDataSource);
        }));

        log.info("==>  数据源加载完毕 -> 初始化动态数据源,共计 {} 条额外数据源", dataSourceMap.size());
    }

    /**
     * 重新加载数据源配置
     */
    public Boolean reload() {
        init();
        DynamicDataSource dataSource = dynamicDataSource();
        dataSource.setTargetDataSources(dataSourceMap);
        dataSource.afterPropertiesSet();
        return Boolean.FALSE;
    }


    /**
     * 数据库事务管理器
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager txManager() {
        DataSourceTransactionManager transactionManager
                = new DataSourceTransactionManager(dynamicDataSource());
        return new ChainedTransactionManager(transactionManager);
    }

    /**
     * 同一个工程中针对多个事务管理器的处理方法
     *
     * @return
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }

    /**
     * 获取数据源
     *
     * @return
     */
    private HikariDataSource getHikariDataSource() {
        // HikariConfig
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(hikariCpDataSourceProperties.getUrl());
        config.setDriverClassName(hikariCpDataSourceProperties.getDriverClassName());
        config.setUsername(hikariCpDataSourceProperties.getUsername());
        config.setPassword(hikariCpDataSourceProperties.getPassword());
        injectHikariConfig(config);

        // HikariDataSource
        return new HikariDataSource(config);
    }

    /**
     * HikariConfig配置填充
     *
     * @param config
     */
    private void injectHikariConfig(HikariConfig config) {
        config.setAutoCommit(true);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    }

}