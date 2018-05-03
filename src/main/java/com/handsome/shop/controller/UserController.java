package com.handsome.shop.controller;

import com.handsome.shop.entity.Address;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    private AddressService addressService;

    @RequestMapping("/phoneExists")
    @ResponseBody
    public boolean phoneExists(String phone) {
        return customerDao.queryByPhone(phone) != null || sellerDao.queryByPhone(phone) != null;
    }

    @PostMapping("/address")
    @ResponseBody
    public boolean addAddress(HttpServletRequest request, String address) {
        Customer customer = getLoginCustomerFromSession(request);
        addressService.addAddress(new Address(customer, address));
        List<Address> addressList = getAddressListFromSession(request);
        if (addressList != null) {
            addressList.add(new Address(null, address));
        }
        return true;
    }

}
