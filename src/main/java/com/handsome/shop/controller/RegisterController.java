package com.handsome.shop.controller;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Seller;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * by wangrongjun on 2018/4/12.
 */
@Controller
public class RegisterController extends BaseController {

    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private CustomerDao customerDao;

    @PostMapping("/register")
    public String register(HttpServletRequest request,
                           String phone,
                           String password,
                           String realName,
                           String nickname,
                           String gender,
                           String identity) {

        if (identity.equals("customer")) {
            if (customerDao.queryByPhone(phone) != null) {
                request.setAttribute("msg", "该手机号已注册");
                return "register";
            }
            Customer customer = new Customer(phone, password, realName, nickname, "man".equals(gender) ? 1 : 0,
                    "/admin/img/user_default_head.jpg");
            customerDao.insert(customer);
            request.setAttribute("phone", phone);
            request.setAttribute("password", password);
            request.setAttribute("identity", identity);
            return "login";
        } else {

            if (sellerDao.queryByPhone(phone) != null) {
                request.setAttribute("msg", "该手机号已注册");
                return "register";
            }
            Seller seller = new Seller(phone, password, realName, nickname, "男".equals(gender) ? 1 : 0,
                    "/admin/img/user_default_head.jpg");
            sellerDao.insert(seller);
            request.setAttribute("phone", phone);
            request.setAttribute("password", password);
            request.setAttribute("identity", identity);
            return "login";
        }
    }

}
