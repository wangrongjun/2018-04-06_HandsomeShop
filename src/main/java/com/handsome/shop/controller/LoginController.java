package com.handsome.shop.controller;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Seller;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.framework.DaoFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by wangrongjun on 2018/4/12.
 */
@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        String phone,
                        String password,
                        String identity,
                        String autoLogin) throws ServletException, IOException {

        removeCookie(request);// 如果客户点击登录了，就移除之前的cookie，防止登录失败时变回cookie的内容。

        // 如果验证码不匹配
//        if (!ImageCode.validateCode(request, validateCode)) {
//            request.setAttribute("msg", "验证码错误");
//            return "login";
//        }

        if (identity.equals("customer")) {// 如果是客户登录
            CustomerDao customerDao = DaoFactory.getCustomerDao();
            Customer customer = customerDao.queryByPhone(phone);
            if (customer != null && password.equals(customer.getPassword())) {
                request.getSession().invalidate();
                request.getSession().setAttribute("customer", customer);
                addCookie(response, phone, password, "customer", autoLogin);
                request.getRequestDispatcher("/").forward(request, response);
                return null;
            } else {
                request.setAttribute("msg", "用户名或密码错误");
                return "login";
            }
        } else {// 如果是商家登录
            SellerDao sellerDao = DaoFactory.getSellerDao();
            Seller seller = sellerDao.queryByPhone(phone);
            if (seller != null && password.equals(seller.getPassword())) {
                request.getSession().invalidate();
                request.getSession().setAttribute("seller", seller);
                addCookie(response, phone, phone, password, "seller");
                request.getRequestDispatcher("/").forward(request, response);
                return null;
            } else {
                request.setAttribute("msg", "用户名或密码错误");
                return "login";
            }
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/");
    }

    private void addCookie(HttpServletResponse response, String phone,
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

    private void removeCookie(HttpServletRequest request) {
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
