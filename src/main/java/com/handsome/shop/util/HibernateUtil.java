package com.handsome.shop.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * by wangrongjun on 2017/9/21.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static Session getSession(String hbm2ddlAuto) {
        if (sessionFactory == null) {
            Configuration configure = new Configuration().configure("hibernate.cfg.xml");
            if (hbm2ddlAuto != null) {
                configure.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
            }
            sessionFactory = configure.buildSessionFactory();
            System.out.println("pool_size: " + configure.getProperty("hibernate.connection.pool_size"));
        }
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

}
