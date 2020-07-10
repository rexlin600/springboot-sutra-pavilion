package xyz.rexlin600.starter.dds;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xyz.rexlin600.starter.dds.config.HikariCpDataSourceProperties;

/**
 * Dynamic data source auto configuration
 *
 * @author hekunlin
 */
@AllArgsConstructor
@EnableConfigurationProperties({HikariCpDataSourceProperties.class})
public class DynamicDataSourceAutoConfiguration {

}