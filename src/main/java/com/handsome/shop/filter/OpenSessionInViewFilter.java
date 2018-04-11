package com.handsome.shop.filter;

import com.wangrj.java_lib.hibernate.HibernateDao;

import javax.servlet.*;
import java.io.IOException;

/**
 * by wangrongjun on 2017/10/7.
 */
public class OpenSessionInViewFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        HibernateDao.openSessionInView = true;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HibernateDao.threadLocal = new ThreadLocal<>();
        try {
            HibernateDao.threadLocal.set(HibernateDao.getSessionFactory().openSession());
            chain.doFilter(request, response);
        } finally {
            HibernateDao.threadLocal.get().close();
        }
    }

    @Override
    public void destroy() {

    }
}
