package com.handsome.shop.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * by wangrongjun on 2018/4/15.
 */
public class SpringProperty extends PropertyPlaceholderConfigurer {

    public static final String ACTIVE_PROFILE = "spring.profiles.active";

    private static Map<String, String> propertyMap = new HashMap<>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            System.out.println("------- " + key + " : " + value);
            propertyMap.put(key, value);
        }
    }

    public static String getProperty(String propertyName) {
        return propertyMap.get(propertyName);
    }

}
