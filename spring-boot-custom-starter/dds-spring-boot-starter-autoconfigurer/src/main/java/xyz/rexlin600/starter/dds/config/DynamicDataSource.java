package xyz.rexlin600.starter.dds.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import xyz.rexlin600.starter.dds.util.DynamicDataSourceContextHolder;

/**
 * 动态数据源
 *
 * @author: rexlin600
 * @date: 2020-03-07
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 根据 key 路由对应的数据源
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}