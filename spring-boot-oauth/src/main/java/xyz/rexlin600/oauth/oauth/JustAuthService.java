package xyz.rexlin600.oauth.oauth;

import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;

/**
 * The interface Oauth oauth service.
 *
 * @author rexlin600
 */
public interface JustAuthService {

	/**
	 * 获取授权地址
	 *
	 * @param source the source
	 * @param state  the state	授权时传一个 state 参数，回调时刻可校验这个参数
	 * @return the string
	 */
	String render(String source, String state);

	/**
	 * 授权回调
	 *
	 * @param source   the source
	 * @param callback the callback
	 * @return the auth response
	 */
	AuthResponse callback(String source, AuthCallback callback);

	/**
	 * 撤销授权
	 *
	 * @param source    the source
	 * @param authToken the auth token
	 * @return the auth response
	 */
	AuthResponse revoke(String source, AuthToken authToken);

	/**
	 * 续期
	 *
	 * @param source    the source
	 * @param authToken the auth token
	 * @return the auth response
	 */
	AuthResponse refresh(String source, AuthToken authToken);

}
