package com.handsome.shop.interceptor;

import com.handsome.shop.framework.HibernateDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * by wangrongjun on 2018/4/14.
 */
public class OpenSessionInViewInterceptor extends HandlerInterceptorAdapter {

    private Transaction transaction;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Session session = HibernateDao.getSession();
        transaction = session.beginTransaction();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            transaction.rollback();
        } else {
            transaction.commit();
        }
        transaction = null;
        HibernateDao.closeSession();
    }

}
