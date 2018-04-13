package com.handsome.shop.controller;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Seller;

import javax.servlet.http.HttpServletRequest;

/**
 * by wangrongjun on 2018/4/14.
 */
public class BaseController {

    protected static final String ATTR_CUSTOMER = "customer";
    protected static final String ATTR_SELLER = "seller";

    protected Customer getLoginCustomer(HttpServletRequest request) {
        return (Customer) request.getSession().getAttribute(ATTR_CUSTOMER);
    }

    protected void setLoginCustomer(HttpServletRequest request, Customer customer) {
        request.getSession().setAttribute(ATTR_CUSTOMER, customer);
    }

    protected Seller getLoginSeller(HttpServletRequest request) {
        return (Seller) request.getSession().getAttribute(ATTR_SELLER);
    }

    protected void setLoginSeller(HttpServletRequest request, Seller seller) {
        request.getSession().setAttribute(ATTR_SELLER, seller);
    }

}
