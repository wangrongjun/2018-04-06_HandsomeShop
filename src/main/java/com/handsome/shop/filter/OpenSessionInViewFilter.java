package com.handsome.shop.filter;


import com.handsome.shop.framework.HibernateDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * by wangrongjun on 2017/10/7.
 */
public class OpenSessionInViewFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        if (requestURI.endsWith(".css") ||
                requestURI.endsWith(".js") ||
                requestURI.endsWith(".jsp") ||
                requestURI.endsWith(".jpg")
                ) {
            chain.doFilter(request, response);
            return;
        }

        HibernateDao.remainSessionActive = true;
        Session session = HibernateDao.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            chain.doFilter(request, response);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            HibernateDao.remainSessionActive = false;
            HibernateDao.closeSession();
        }
    }

    @Override
    public void destroy() {
    }
}
