package com.handsome.shop.framework;

import com.handsome.shop.constant.C;
import com.handsome.shop.entity.Contact;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Orders;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.util.Pager;
import com.wangrj.java_lib.java_util.ReflectUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
public class BaseController {

    protected boolean isLogin(HttpServletRequest request) {
        return getLoginCustomerFromSession(request) != null || getLoginSellerFromSession(request) != null;
    }

    protected Customer getLoginCustomerFromSession(HttpServletRequest request) {
        return (Customer) request.getSession().getAttribute(C.SESSION_CUSTOMER);
    }

    protected void setLoginCustomerToSession(HttpServletRequest request, Customer customer) {
        request.getSession().setAttribute(C.SESSION_CUSTOMER, customer);
    }

    protected Seller getLoginSellerFromSession(HttpServletRequest request) {
        return (Seller) request.getSession().getAttribute(C.SESSION_SELLER);
    }

    protected void setLoginSellerToSession(HttpServletRequest request, Seller seller) {
        request.getSession().setAttribute(C.SESSION_SELLER, seller);
    }

    @SuppressWarnings("unchecked")
    protected List<Contact> getAddressListFromSession(HttpServletRequest request) {
        return (List<Contact>) request.getSession().getAttribute(C.SESSION_ADDRESS_LIST);
    }

    @SuppressWarnings("unchecked")
    protected void setAddressListFromSession(HttpServletRequest request, List<Contact> addressList) {
        request.getSession().setAttribute(C.SESSION_ADDRESS_LIST, addressList);
    }

    protected BindingResult createBR() {
        return new MapBindingResult(new HashMap<>(), null);
    }

}
