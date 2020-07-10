package xyz.rexlin600.starter.dds.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.rexlin600.starter.dds.util.DynamicDataSourceContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dynamic datasource interceptor
 *
 * @author hekunlin
 */
@Slf4j
@Component
@AllArgsConstructor
public class DynamicDatasourceInterceptor implements HandlerInterceptor {

	/**
	 * Pre handle boolean
	 *
	 * @param request  request
	 * @param response response
	 * @param handler  handler
	 * @return the boolean
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		DynamicDataSourceContextHolder.push(1L);
		return true;
	}

	/**
	 * After completion *
	 *
	 * @param request  request
	 * @param response response
	 * @param handler  handler
	 * @param ex       ex
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
								@Nullable Exception ex) {
		DynamicDataSourceContextHolder.remove();
	}

}
