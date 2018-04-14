package com.handsome.shop.framework;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Seller;
import com.handsome.shop.constant.C;

import javax.servlet.http.HttpServletRequest;

/**
 * by wangrongjun on 2018/4/14.
 */
public class BaseController {

    protected Customer getLoginCustomer(HttpServletRequest request) {
        return (Customer) request.getSession().getAttribute(C.SESSION_CUSTOMER);
    }

    protected void setLoginCustomer(HttpServletRequest request, Customer customer) {
        request.getSession().setAttribute(C.SESSION_CUSTOMER, customer);
    }

    protected Seller getLoginSeller(HttpServletRequest request) {
        return (Seller) request.getSession().getAttribute(C.SESSION_SELLER);
    }

    protected void setLoginSeller(HttpServletRequest request, Seller seller) {
        request.getSession().setAttribute(C.SESSION_SELLER, seller);
    }

}
