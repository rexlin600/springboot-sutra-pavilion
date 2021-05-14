package xyz.rexlin600.oauth.rest;

import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.oauth.common.util.AuthSourceUtil;
import xyz.rexlin600.oauth.oauth.OauthServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Call back rest.
 *
 * @author rexlin600
 */
@RestController
@RequestMapping("/oauth")
public class OauthRest {

	/**
	 * The Gitee oauth service.
	 */
	private OauthServiceImpl oauthServiceImpl;

	/**
	 * Instantiates a new Gitee call back rest.
	 *
	 * @param oauthServiceImpl the gitee oauth service
	 */
	@Autowired
	public OauthRest(OauthServiceImpl oauthServiceImpl) {
		this.oauthServiceImpl = oauthServiceImpl;
	}

	/**
	 * 渲染授权地址
	 *
	 * @param source   the source
	 * @param response the response
	 * @throws IOException   the io exception
	 * @throws AuthException the auth exception
	 */
	@GetMapping("/render/{source}")
	public void render(@PathVariable(value = "source") String source, HttpServletResponse response) throws IOException, AuthException {
		// 校验 source
		boolean flag = AuthSourceUtil.checkSource(source);
		if (!flag) {
			throw new AuthException(AuthResponseStatus.UNIDENTIFIED_PLATFORM);
		}

		// 获取授权地址 URL
		String authorizeUrl = oauthServiceImpl.render(source, AuthStateUtils.createState());

		response.sendRedirect(authorizeUrl);
	}

	/**
	 * 回调函数
	 *
	 * @param source       the source
	 * @param authCallback the auth callback
	 */
	@GetMapping("/callback/{source}")
	public void callback(@PathVariable(value = "source") String source, AuthCallback authCallback) {
		// 校验 source
		boolean flag = AuthSourceUtil.checkSource(source);
		if (!flag) {
			throw new AuthException(AuthResponseStatus.UNIDENTIFIED_PLATFORM);
		}

		oauthServiceImpl.callback(source, authCallback);
	}


}
