package xyz.rexlin600.starter.dds.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import xyz.rexlin600.starter.dds.util.DynamicDataSourceContextHolder;

/**
 * Dynamic data source
 *
 * @author hekunlin
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * Determine current lookup key object
	 *
	 * @return the object
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		Long ds = DynamicDataSourceContextHolder.get();
		log.info("==>  当前数据源ID:{}", ds);
		return ds;
	}

}