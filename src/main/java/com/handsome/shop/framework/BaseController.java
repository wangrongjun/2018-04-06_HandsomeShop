package com.handsome.shop.framework;

import com.handsome.shop.bean.Address;
import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Seller;
import com.handsome.shop.constant.C;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
public class BaseController {

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
    protected List<Address> getAddressListFromSession(HttpServletRequest request) {
        return (List<Address>) request.getSession().getAttribute(C.SESSION_ADDRESS_LIST);
    }

    @SuppressWarnings("unchecked")
    protected void setAddressListFromSession(HttpServletRequest request, List<Address> addressList) {
        request.getSession().setAttribute(C.SESSION_ADDRESS_LIST, addressList);
    }

}
