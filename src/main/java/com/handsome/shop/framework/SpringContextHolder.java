package com.handsome.shop.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * by wangrongjun on 2018/4/15.
 */
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("设置了applicationContext");
        SpringContextHolder.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }

    public static <T> T getBean(Class<T> beanClass) {
        assertContextInjected();
        return applicationContext.getBean(beanClass);
    }

    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException(
                    "applicationContext未设置");
        }
    }

}
