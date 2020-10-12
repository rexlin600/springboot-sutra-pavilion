package xyz.rexlin600.oauth.common.config;

import lombok.Data;
import me.zhyd.oauth.config.AuthDefaultSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.oauth.common.constant.OauthConst;

import java.util.Map;

/**
 * The type Gitee config.
 *
 * @author rexlin600
 */
@Data
@ConfigurationProperties(prefix = OauthConst.JUST_AUTH)
@Configuration
public class OauthConfig {

	/**
	 * The Map.
	 */
	private Map<AuthDefaultSource, String> map;

}
