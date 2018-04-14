package com.handsome.shop.controller;

import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * by wangrongjun on 2018/4/13.
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private CustomerDao customerDao;

    @RequestMapping("/phoneExists")
    @ResponseBody
    public boolean phoneExists(String phone) {
        return customerDao.queryByPhone(phone) != null || sellerDao.queryByPhone(phone) != null;
    }

}
