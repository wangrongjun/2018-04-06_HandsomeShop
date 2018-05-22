package com.handsome.shop.test;

import com.wangrj.java_lib.math.encrypt.rsa.RSA;

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
        List<String> list = Arrays.asList("a", "c", "b", "d");
        String s = list.stream().sorted().map(String::toUpperCase).collect(Collectors.joining(","));
        System.out.println(s);

//        LocalDateTime dateTime = LocalDateTime.now();
//        String s = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(s);
    }

}
