package com.handsome.shop.controller;

import com.handsome.shop.constant.C;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.framework.BaseController;
import com.wangrj.web_lib.util.ImageCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by wangrongjun on 2018/4/12.
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private SellerDao sellerDao;
    @Resource
    private CustomerDao customerDao;

    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam String phone,
                        @RequestParam String password,
                        @RequestParam String identity,
                        @RequestParam String validateCode,
                        String autoLogin) throws IOException {

        removeCookie(request);// 如果客户点击登录了，就移除之前的cookie，防止登录失败时变回cookie的内容。

        // 如果验证码不匹配
        if (!ImageCode.validateCode(request, validateCode)) {
            request.setAttribute("msg", "验证码错误");
            return "login";
        }

        if (identity.equals(C.SESSION_CUSTOMER)) {// 如果是客户登录
            Customer customer = customerDao.queryByPhone(phone);
            if (customer != null && password.equals(customer.getPassword())) {
                request.getSession().invalidate();
                setLoginCustomerToSession(request, customer);
                addCookie(response, phone, password, C.SESSION_CUSTOMER, autoLogin);
//                request.getRequestDispatcher("/").forward(request, response);
                response.sendRedirect("/");
                return null;
            } else {
                request.setAttribute("msg", "用户名或密码错误");
                return "login";
            }
        } else {// 如果是卖家登录
            Seller seller = sellerDao.queryByPhone(phone);
            if (seller != null && password.equals(seller.getPassword())) {
                request.getSession().invalidate();
                setLoginSellerToSession(request, seller);
                addCookie(response, phone, password, C.SESSION_SELLER, autoLogin);
//                request.getRequestDispatcher("/").forward(request, response);
                response.sendRedirect("/");
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
