package xyz.rexlin600.starter.dds.annotation;

import org.springframework.context.annotation.Import;
import xyz.rexlin600.starter.dds.config.DynamicDataSourceConfig;

import java.lang.annotation.*;

/**
 * @author Lucky
 * @date 2019-05-18
 * <p>
 * 开启动态数据源
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({DynamicDataSourceConfig.class})
public @interface EnableDynamicDataSource {
}
