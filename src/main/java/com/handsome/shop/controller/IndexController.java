package com.handsome.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * by wangrongjun on 2017/11/2.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/a.test", method = RequestMethod.GET)
    public String testAAA() {
        return "test";
    }

}
