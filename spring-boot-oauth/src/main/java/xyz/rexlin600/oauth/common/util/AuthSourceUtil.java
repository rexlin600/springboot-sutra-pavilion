package xyz.rexlin600.oauth.common.util;

import cn.hutool.core.map.MapUtil;
import me.zhyd.oauth.config.AuthDefaultSource;
import xyz.rexlin600.oauth.common.config.OauthConfig;

import java.util.Arrays;
import java.util.Map;

/**
 * The type Auth source util.
 *
 * @author rexlin600
 */
public class AuthSourceUtil {

	/**
	 * Check source boolean.
	 *
	 * @param source the source
	 * @return the boolean
	 */
	public static boolean checkSource(String source) {
		AuthDefaultSource[] values = AuthDefaultSource.values();
		return Arrays.stream(values).anyMatch(m -> m.getName().equals(source.toUpperCase()));
	}

	/**
	 * Check source boolean.
	 *
	 * @param source the source
	 * @param config the config
	 * @return the boolean
	 */
	public static boolean checkSource(String source, OauthConfig config) {
		Map<AuthDefaultSource, String> map = config.getMap();
		if (MapUtil.isEmpty(map)) {
			return false;
		}

		if (checkSource(source) && map.containsKey(source.toUpperCase())) {
			return true;
		}

		return false;
	}


}
