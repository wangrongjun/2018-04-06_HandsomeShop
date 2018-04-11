package com.handsome.shop.listener;

import com.wangrj.java_lib.hibernate.HibernateDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * by wangrongjun on 2017/9/27.
 */
public class HibernateInitAndDestroyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateDao.buildSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateDao.closeSessionFactory();
    }

}
