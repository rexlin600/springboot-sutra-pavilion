package xyz.rexlin600.starter.dds.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import xyz.rexlin600.starter.dds.util.DynamicDataSourceContextHolder;

/**
 * 动态数据源
 *
 * @author: rexlin600
 * @since: 2020-03-07
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 根据 key 路由对应的数据源
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        Long ds = DynamicDataSourceContextHolder.get();
        log.info("==>  当前数据源ID:{}", ds);
        return ds;
    }

}