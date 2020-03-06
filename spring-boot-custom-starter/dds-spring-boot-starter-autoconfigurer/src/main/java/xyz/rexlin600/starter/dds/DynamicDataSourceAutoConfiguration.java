package xyz.rexlin600.starter.dds;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xyz.rexlin600.starter.dds.config.HikariCpDataSourceProperties;

/**
 * 动态数据源自动配置类
 *
 * @author: rexlin600
 * @date: 2020-03-07
 */
@AllArgsConstructor
@EnableConfigurationProperties({HikariCpDataSourceProperties.class})
public class DynamicDataSourceAutoConfiguration {

}