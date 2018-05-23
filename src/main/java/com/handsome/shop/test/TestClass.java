package com.handsome.shop.test;

import com.wangrj.java_lib.math.encrypt.rsa.RSA;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * by wangrongjun on 2017/11/3.
 */
public class TestClass {

    public static void main(String[] args) {
        try {
            Constructor[] constructors = HttpStatus.class.getDeclaredConstructors();
            Constructor constructor = constructors[0];
            constructor.setAccessible(true);
            HttpStatus httpStatus = (HttpStatus) constructor.newInstance("One", 2, 400, "errorMsg");
            System.out.println(httpStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
