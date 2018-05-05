package com.handsome.shop.controller;

import com.handsome.shop.dao.ContactDao;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.entity.Contact;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.framework.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/contact")
    @ResponseBody
    public boolean addContact(HttpServletRequest request,
                              @RequestParam String phone,
                              @RequestParam String receiver,
                              @RequestParam String address,
                              @RequestParam(defaultValue = "false") Boolean defaultContact) {
        Customer customer = getLoginCustomerFromSession(request);
        Contact contact = new Contact(customer, phone, receiver, address, defaultContact);
        contactDao.insert(contact);
        List<Contact> addressList = getAddressListFromSession(request);
        if (addressList != null) {
            addressList.add(contact);
        }
        return true;
    }

}
