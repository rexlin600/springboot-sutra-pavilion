//package xyz.rexlin600.github.config.filter;
//
//import org.springframework.util.StringUtils;
//import xyz.rexlin600.github.common.constant.HeaderConstant;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * TODO github filter
// *
// * @author: hekunlin
// * @since: 2020/1/3
// */
//@WebFilter(filterName = "githubTokenFilter", urlPatterns = "/*")
//public class GithubTokenFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//
//        // get token
//        String token = httpServletRequest.getHeader(HeaderConstant.TOKEN_HEADER);
//        if (StringUtils.isEmpty(token)) {
//
//        }
//
//    }
//
//
//}