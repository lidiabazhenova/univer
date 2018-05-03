package com.lidiabazhenova.webapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "*.do")
public class LoginRequeredFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        System.out.println(request.getRequestURI());

        if (request.getSession().getAttribute("username") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher dis = request.getRequestDispatcher("/login.do");
            dis.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
