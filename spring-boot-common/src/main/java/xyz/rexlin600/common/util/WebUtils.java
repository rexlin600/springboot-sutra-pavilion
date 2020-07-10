package xyz.rexlin600.common.util;

import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Web utils
 *
 * @author hekunlin
 */
@Slf4j
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {

	/**
	 * Is ajax boolean
	 *
	 * @param handlerMethod handler method
	 * @return the boolean
	 */
	public boolean isAjax(HandlerMethod handlerMethod) {
		ResponseBody responseBody = ClassUtils.getAnnotation(handlerMethod, ResponseBody.class);
		return responseBody != null;
	}

	/**
	 * Gets cookie val *
	 *
	 * @param name name
	 * @return the cookie val
	 */
	public String getCookieVal(String name) {
		HttpServletRequest request = WebUtils.getRequest();
		Assert.notNull(request, "request from RequestContextHolder is null");
		return getCookieVal(request, name);
	}

	/**
	 * Gets cookie val *
	 *
	 * @param request request
	 * @param name    name
	 * @return the cookie val
	 */
	public String getCookieVal(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * Remove cookie *
	 *
	 * @param response response
	 * @param key      key
	 */
	public void removeCookie(HttpServletResponse response, String key) {
		setCookie(response, key, null, 0);
	}

	/**
	 * Sets cookie *
	 *
	 * @param response        response
	 * @param name            name
	 * @param value           value
	 * @param maxAgeInSeconds max age in seconds
	 */
	public void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	/**
	 * Gets request *
	 *
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		try {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			return ((ServletRequestAttributes) requestAttributes).getRequest();
		} catch (IllegalStateException e) {
			return null;
		}
	}

	/**
	 * Gets response *
	 *
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * Render json *
	 *
	 * @param response response
	 * @param result   result
	 */
	public void renderJson(HttpServletResponse response, Object result) {
		renderJson(response, result, MediaType.APPLICATION_JSON_VALUE);
	}

	/**
	 * Render json *
	 *
	 * @param response    response
	 * @param result      result
	 * @param contentType content type
	 */
	public void renderJson(HttpServletResponse response, Object result, String contentType) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(contentType);
		try (PrintWriter out = response.getWriter()) {
			out.append(JSONUtil.toJsonStr(result));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

}
