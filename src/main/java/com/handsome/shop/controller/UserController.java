package com.handsome.shop.controller;

import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.framework.DaoFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * by wangrongjun on 2018/4/13.
 */
@Controller
public class UserController {

    @RequestMapping("/phoneExists")
    @ResponseBody
    public boolean phoneExists(String phone) {
        CustomerDao customerDao = DaoFactory.getCustomerDao();
        return customerDao.queryByPhone(phone) != null;
    }

}
