package com.handsome.shop.framework;

import com.handsome.shop.constant.C;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Seller;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.LobCreator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;

/**
 * by wangrongjun on 2018/4/14.
 */
public class BaseController {

    @Resource
    private SessionFactory sessionFactory;

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

    protected BindingResult createBR() {
        return new MapBindingResult(new HashMap<>(), null);
    }

    protected Blob toBlob(InputStream is) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        LobCreator lobCreator = Hibernate.getLobCreator(session);
        return lobCreator.createBlob(is, is.available());
    }

}
