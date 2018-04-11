package com.handsome.shop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * by wangrongjun on 2017/9/28.
 */
public class ValidateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        req.getRequestURI();
    }

    @Override
    public void destroy() {

    }
}
