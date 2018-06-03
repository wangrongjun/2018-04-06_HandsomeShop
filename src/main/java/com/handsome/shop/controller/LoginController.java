package com.handsome.shop.controller;

import com.handsome.shop.constant.C;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.framework.BaseController;
import com.wangrj.java_lib.java_util.DataUtil;
import com.wangrj.java_lib.web.ImageCode;
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

        removeCookie(response);// 如果客户点击登录了，就移除之前的cookie，防止登录失败时变回cookie的内容。

        // 如果验证码不匹配
        if (!ImageCode.validateCode(request, validateCode)) {
            request.setAttribute("msg", "验证码错误");
            return "login";
        }

        password = DataUtil.md5(password);// 密码转换为密文
        if (identity.equals(C.SESSION_CUSTOMER)) {// 如果是客户登录
            Customer customer = customerDao.queryByPhone(phone);
            if (customer != null && password.equals(customer.getPassword())) {
                request.getSession().invalidate();
                setLoginCustomerToSession(request, customer);
                if ("true".equals(autoLogin)) {
                    addCookie(response, phone, password, C.SESSION_CUSTOMER, autoLogin);
                }
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
                if ("true".equals(autoLogin)) {
                    addCookie(response, phone, password, C.SESSION_SELLER, autoLogin);
                }
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
        removeCookie(response);
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

    private void removeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("phone", null);
        cookie.setMaxAge(0);// 通过设置有效期为0来移除
        response.addCookie(cookie);

        cookie = new Cookie("password", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        cookie = new Cookie("identity", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        cookie = new Cookie("autoLogin", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("phone".equals(cookie.getName()) ||
//                        "password".equals(cookie.getName()) ||
//                        "identity".equals(cookie.getName()) ||
//                        "autoLogin".equals(cookie.getName())) {
//                    cookie.setMaxAge(0);// 通过设置有效期为0来移除
//                }
//            }
//        }
    }

}
