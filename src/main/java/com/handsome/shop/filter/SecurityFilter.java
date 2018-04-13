package com.handsome.shop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
public class SecurityFilter implements Filter {

    private String[] notAllowIfNotLoginUrls = new String[]{
            "/shop_car.jsp",
            "/customer_order_list.jsp",
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();

        boolean allowIfNotLogin = true;
        for (String url : notAllowIfNotLoginUrls) {
            String requestURI = ((HttpServletRequest) request).getRequestURI();
            if (requestURI.endsWith(url)) {
                allowIfNotLogin = false;
                break;
            }
        }

        if (allowIfNotLogin) {
            chain.doFilter(request, response);
        } else {
            if (session.getAttribute("customer") == null && session.getAttribute("seller") == null) {
                request.setAttribute("msg", "请登录");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
