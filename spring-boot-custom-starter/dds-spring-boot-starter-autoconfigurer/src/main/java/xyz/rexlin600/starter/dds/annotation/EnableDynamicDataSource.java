package xyz.rexlin600.starter.dds.annotation;

import org.springframework.context.annotation.Import;
import xyz.rexlin600.starter.dds.config.DynamicDataSourceConfig;

import java.lang.annotation.*;

/**
 * Enable dynamic data source
 *
 * @author hekunlin
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({DynamicDataSourceConfig.class})
public @interface EnableDynamicDataSource {
}
