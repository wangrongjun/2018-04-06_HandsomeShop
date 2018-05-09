package com.handsome.shop.controller;

import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Picture;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.framework.BaseController;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;

/**
 * by wangrongjun on 2018/4/12.
 */
@Controller
public class RegisterController extends BaseController {

    @Resource
    private SellerDao sellerDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private SessionFactory sessionFactory;// TODO 这个东西不利于测试，以后想办法移到dao层！

    @PostMapping("/register")
    public String register(HttpServletRequest request,
                           String phone,
                           String password,
                           String realName,
                           String nickname,
                           String gender,
                           String identity) throws IOException {

        if (identity.equals("customer")) {
            if (customerDao.queryByPhone(phone) != null) {
                request.setAttribute("msg", "该手机号已注册");
                return "register";
            }
            FileInputStream fis = new FileInputStream("src/main/webapp/admin/img/user_default_head.jpg");
            // TODO 以后OpenSessionInView不用之后，要改为openSession
            Blob pictureData = sessionFactory.getCurrentSession().getLobHelper().createBlob(fis, fis.available());
            Picture head = new Picture(Picture.PictureType.jpg, pictureData);
            Customer customer = new Customer(phone, password, realName, nickname, "man".equals(gender) ? "男" : "女", head);
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
            FileInputStream fis = new FileInputStream("src/main/webapp/admin/img/user_default_head.jpg");
            // TODO 以后OpenSessionInView不用之后，要改为openSession
            Blob pictureData = sessionFactory.getCurrentSession().getLobHelper().createBlob(fis, fis.available());
            Picture head = new Picture(Picture.PictureType.jpg, pictureData);
            Seller seller = new Seller(phone, password, realName, nickname, "男".equals(gender) ? "男" : "女", head);
            sellerDao.insert(seller);
            request.setAttribute("phone", phone);
            request.setAttribute("password", password);
            request.setAttribute("identity", identity);
            return "login";
        }
    }

}
