package com.handsome.shop.filter;

import com.handsome.shop.constant.C;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
public class PageSecurityFilter implements Filter {

    // TODO 还有一些Page不是跳到登录页，而是重定向主页，如 "/index/jsp"

    //以下文件都禁止直接访问，需要重定向到首页
//        "/index.jsp"
//        "/goods_box.jsp"
//        "/search_goods.jsp"
//        "/search_goods_type.jsp"
//        "/goods_info.jsp"
//        "/shop_detail.jsp",
//        "/customer_order_list.jsp"

    private static String[] notAllowIfNotLoginUrls = new String[]{
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
            if (session.getAttribute(C.SESSION_CUSTOMER) == null && session.getAttribute(C.SESSION_SELLER) == null) {
                ((HttpServletResponse) response).sendRedirect("/login.jsp");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
