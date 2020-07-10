package xyz.rexlin600.github.config.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cors filter
 *
 * @author hekunlin
 */
@SuppressWarnings("Duplicates")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	/**
	 * OPTIONS
	 */
	private static final String OPTIONS = "OPTIONS";

	/**
	 * Do filter *
	 *
	 * @param req   req
	 * @param res   res
	 * @param chain chain
	 * @throws IOException      io exception
	 * @throws ServletException servlet exception
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "token,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
		response.setHeader("Access-Control-Max-Age", "3600");

		if (!OPTIONS.equals(request.getMethod())) {
			chain.doFilter(req, res);
		}
	}
}