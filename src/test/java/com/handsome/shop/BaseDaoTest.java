package com.handsome.shop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * by wangrongjun on 2018/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dataSource.xml"})
@ActiveProfiles("test")
public class BaseDaoTest {

    @Resource
    private SessionFactory sessionFactory;
    protected Session session;

    @Before
    public void initSession() {
        // initSession 和 closeSession 的代码，都是参考 Spring 的 OpenSessionInViewInterceptor
        session = sessionFactory.openSession();
//        session.beginTransaction();
//        SessionHolder sessionHolder = new SessionHolder(session);
//        TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
    }

    @After
    public void closeSession() {
//        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
//        Session session = sessionHolder.getSession();
//        session.getTransaction().commit();
        session.close();
    }

}
