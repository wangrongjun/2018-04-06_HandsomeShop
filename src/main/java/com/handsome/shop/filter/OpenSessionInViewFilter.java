package com.handsome.shop.filter;


import com.handsome.shop.framework.HibernateDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = HibernateDao.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            chain.doFilter(request, response);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            HibernateDao.closeSessionAfterView();
        }
    }

    @Override
    public void destroy() {

    }
}
