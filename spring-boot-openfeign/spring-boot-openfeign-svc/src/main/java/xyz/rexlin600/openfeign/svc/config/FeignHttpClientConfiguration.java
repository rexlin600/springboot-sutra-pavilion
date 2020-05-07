package xyz.rexlin600.openfeign.svc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Feign HttpClient 配置类
 *
 * @author: hekunlin
 * @date: 2020/5/7
 */
@Import(FeignAutoConfiguration.class)
@Configuration
public class FeignHttpClientConfiguration {

    // 启用 apache HttpClient 连接池

}