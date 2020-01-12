package xyz.rexlin600.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * SessionConfig 配置
 * <p>
 * // 默认 30 天   86400 * 30
 *
 * @author rexlin600
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {

    /**
     * 增加下面这个配置的原因 https://www.cnblogs.com/coderzl/p/7512644.html
     * <p>
     * https://github.com/spring-projects/spring-session/issues/124
     *
     * @return
     */
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

}