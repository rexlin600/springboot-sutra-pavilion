package xyz.rexlin600.starter.dds.annotation;

import org.springframework.context.annotation.Import;
import xyz.rexlin600.starter.dds.config.DynamicDataSourceConfig;

import java.lang.annotation.*;

/**
 * 开启动态数据源
 *
 * @author rexlin600
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({DynamicDataSourceConfig.class})
public @interface EnableDynamicDataSource {
}
