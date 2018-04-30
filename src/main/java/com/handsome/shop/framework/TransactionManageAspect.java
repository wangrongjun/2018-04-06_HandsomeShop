package com.handsome.shop.framework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.annotation.Resource;

/**
 * by wangrongjun on 2018/4/17.
 */
public class TransactionManageAspect {

    @Resource
    private SessionFactory sessionFactory;

    public void beginTransaction() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }

    public void commitTransaction() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.commit();
    }

    public void rollbackTransaction() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.rollback();
    }

}
