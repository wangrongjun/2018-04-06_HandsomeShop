package com.handsome.shop.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * by wangrongjun on 2018/5/3.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnObjectToJsonIgnoreFields {
    String[] value() default {};
}
