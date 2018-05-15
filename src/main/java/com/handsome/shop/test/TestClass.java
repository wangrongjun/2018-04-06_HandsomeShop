package com.handsome.shop.test;

import com.wangrj.java_lib.math.encrypt.rsa.RSA;

import java.util.HashSet;
import java.util.Set;

/**
 * by wangrongjun on 2017/11/3.
 */
public class TestClass {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Set<String> set = new HashSet<>();
            set.add("a");
            set.add("b");
            set.add("c");
            set.add("d");
            if (set.iterator().next().equals("a")) {
                System.out.println("true");
            } else {
                System.out.println("false ------------------------------");
            }
        }
    }

}
