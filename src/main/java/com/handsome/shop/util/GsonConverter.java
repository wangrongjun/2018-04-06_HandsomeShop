package com.handsome.shop.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import org.hibernate.proxy.HibernateProxy;

/**
 * by wangrongjun on 2018/5/3.
 */
public class GsonConverter {

    /**
     * 该方法专门用来解析Hibernate查询返回的对象。
     * <p>
     * 该方法可以避免：
     * 1. Hibernate使用延迟加载时，Gson无法解析HibernateProxy代理对象而抛出异常。
     * 2. 一对多的两个实体类相互引用，导致Gson解析进入死循环的情况。
     * <p>
     * 问题解决的原理：
     * 1. 碰到HibernateProxy的子类类型，跳过不解析。
     * 2. 通过传入忽略字段来打断死循环。
     *
     * @param ignoreFieldNameList such as "goods" or "GoodsImage.goods"
     */
    public static String toJson(Object object, String... ignoreFieldNameList) {
        ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if (ignoreFieldNameList == null || ignoreFieldNameList.length == 0) {
                    return false;
                }
                // TODO 把循环得出结果用一个Map来缓存
                for (String ignoreFieldName : ignoreFieldNameList) {
                    if (ignoreFieldName.contains(".")) {
                        String[] split = ignoreFieldName.split("\\.");
                        String declaringClassName = split[0];
                        ignoreFieldName = split[1];
                        if (f.getDeclaringClass().getSimpleName().equals(declaringClassName) &&
                                f.getName().equals(ignoreFieldName)) {
                            return true;
                        }
                    } else {
                        if (f.getName().equals(ignoreFieldName)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return HibernateProxy.class.isAssignableFrom(clazz);
            }
        };

        return new GsonBuilder().
                setPrettyPrinting().
                setExclusionStrategies(exclusionStrategy).
                create().
                toJson(object);
    }

}
