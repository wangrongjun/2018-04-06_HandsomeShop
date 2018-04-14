package com.handsome.shop.interceptor;

import com.handsome.shop.constant.C;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * by wangrongjun on 2018/4/14.
 */
public class RequestSecurityInterceptor extends HandlerInterceptorAdapter {

    private static String[] notAllowIfNotLoginRequests = new String[]{
            "/logout",
            "/orders",
            "/shopCar",
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean allowIfNotLogin = true;
        for (String notAllowIfNotLoginRequest : notAllowIfNotLoginRequests) {
            String requestURI = request.getRequestURI();
            if (requestURI.contains(notAllowIfNotLoginRequest)) {
                allowIfNotLogin = false;
                break;
            }
        }

        if (allowIfNotLogin) {
            return true;
        } else {
            Object customer = request.getSession().getAttribute(C.SESSION_CUSTOMER);
            Object seller = request.getSession().getAttribute(C.SESSION_SELLER);
            if (customer == null && seller == null) {
                response.sendRedirect("/login.jsp");
//                request.setAttribute("msg", "请登录");
//                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return false;
            } else {
                return true;
            }
        }
    }

}
