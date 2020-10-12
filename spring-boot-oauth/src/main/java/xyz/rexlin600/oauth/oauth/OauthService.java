package xyz.rexlin600.oauth.oauth;

import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.rexlin600.oauth.common.config.OauthConfig;
import xyz.rexlin600.oauth.common.util.AuthSourceUtil;

/**
 * @author rexlin600
 */
@Service
public class OauthService implements JustAuthService {

	private final OauthConfig config;

	@Autowired
	public OauthService(OauthConfig config) {
		this.config = config;
	}

	@Override
	public String render(String source, String state) {
		boolean flag = AuthSourceUtil.checkSource(source, config);
		if (!flag) {
			throw new AuthException(AuthResponseStatus.UNIDENTIFIED_PLATFORM);
		}


		return null;
	}

	@Override
	public AuthResponse callback(String source, AuthCallback callback) {
		return null;
	}

	@Override
	public AuthResponse revoke(String source, AuthToken authToken) {
		return null;
	}

	@Override
	public AuthResponse refresh(String source, AuthToken authToken) {
		return null;
	}
}
