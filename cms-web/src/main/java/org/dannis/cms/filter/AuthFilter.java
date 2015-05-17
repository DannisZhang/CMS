package org.dannis.cms.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-17 02:02
 */
public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        //do nothing
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        String uri = httpServletRequest.getRequestURI();
        String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+httpServletRequest.getContextPath();
        if (username == null && !uri.contains("login.html")) {
            System.out.println("跳转登录");
            httpServletResponse.sendRedirect(basePath + "/login.html");
//            httpServletRequest.getRequestDispatcher("login.html").forward(servletRequest,servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        //do nothing
    }
}
