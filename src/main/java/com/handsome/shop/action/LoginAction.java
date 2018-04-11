package com.handsome.shop.action;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Seller;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.framework.DaoFactory;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.framework.ActionSupport;
import com.wangrj.web_lib.util.ImageCode;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * by wangrongjun on 2017/6/17.
 */
public class LoginAction extends ActionSupport {
    @Override
    protected String execute() throws ParamErrorException, ServletException, IOException {

        removeCookie();// 如果客户点击登录了，就移除之前的cookie，防止登录失败时变回cookie的内容。

        String phone = checkStringParameter("phone");
        String password = checkStringParameter("password");
        String identity = checkStringParameter("identity");
        String autoLogin = checkStringParameter("autoLogin");
        String validateCode = checkStringParameter("validateCode");

        // 如果验证码不匹配
        if (!ImageCode.validateCode(request, validateCode)) {
            request.setAttribute("msg", "验证码错误");
            return "login.jsp";
        }

        if (identity.equals("customer")) {// 如果是客户登录
            CustomerDao customerDao = DaoFactory.getCustomerDao();
            Customer customer = customerDao.queryByPhone(phone);
            if (customer != null && password.equals(customer.getPassword())) {
                request.getSession().invalidate();
                request.getSession().setAttribute("customer", customer);
                addCookie(phone, password, "customer", autoLogin);
                return "-index.jsp";
            } else {
                request.setAttribute("msg", "用户名或密码错误");
                return "login.jsp";
            }
        } else {// 如果是商家登录
            SellerDao sellerDao = DaoFactory.getSellerDao();
            Seller seller = sellerDao.queryByPhone(phone);
            if (seller != null && password.equals(seller.getPassword())) {
                request.getSession().invalidate();
                request.getSession().setAttribute("seller", seller);
                addCookie(phone, phone, password, "seller");
                return "-index_seller.jsp";
            } else {
                request.setAttribute("msg", "用户名或密码错误");
                return "login.jsp";
            }
        }
    }

    private void addCookie(String phone,
                           String password,
                           String identity,
                           String autoLogin) {
        Cookie cookie = new Cookie("phone", phone);
        cookie.setMaxAge(7 * 24 * 60 * 60);// 有效期为7天
        response.addCookie(cookie);

        cookie = new Cookie("password", password);
        cookie.setMaxAge(7 * 24 * 60 * 60);// 有效期为7天
        response.addCookie(cookie);

        cookie = new Cookie("identity", identity);
        cookie.setMaxAge(7 * 24 * 60 * 60);// 有效期为7天
        response.addCookie(cookie);

        cookie = new Cookie("autoLogin", autoLogin);
        cookie.setMaxAge(7 * 24 * 60 * 60);// 有效期为7天
        response.addCookie(cookie);
    }

    private void removeCookie() {
//        Cookie cookie = new Cookie("phone", null);
//        cookie.setMaxAge(0);// 有效期为0，即马上失效
//        response.addCookie(cookie);
//        cookie = new Cookie("password", null);
//        cookie.setMaxAge(0);// 有效期为7天
//        response.addCookie(cookie);
//        cookie = new Cookie("identity", null);
//        cookie.setMaxAge(0);// 有效期为7天
//        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("phone".equals(cookie.getName()) ||
                    "password".equals(cookie.getName()) ||
                    "identity".equals(cookie.getName()) ||
                    "autoLogin".equals(cookie.getName())) {
                cookie.setMaxAge(0);// 通过设置有效期为0来移除
            }
        }
    }
}
