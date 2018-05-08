package com.handsome.shop.controller;

import com.handsome.shop.dao.ContactDao;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.entity.Contact;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.framework.ReturnObjectToJsonIgnoreFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2018/4/13.
 */
@Controller
public class UserController extends BaseController {

    @Resource
    private SellerDao sellerDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private ContactDao contactDao;

    @RequestMapping("/phoneExists")
    @ResponseBody
    public boolean phoneExists(String phone) {
        return customerDao.queryByPhone(phone) != null || sellerDao.queryByPhone(phone) != null;
    }

    @GetMapping("/contact")
    @ReturnObjectToJsonIgnoreFields("customer")
    public List<Contact> showContacts(HttpServletRequest request) {
        Customer customer = getLoginCustomerFromSession(request);
        // TODO 创建一个显示所有收货地址的页面
        return contactDao.queryByCustomerId(customer.getCustomerId());
    }

}
