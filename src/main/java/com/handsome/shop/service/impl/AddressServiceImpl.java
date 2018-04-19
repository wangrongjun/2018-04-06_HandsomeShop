package com.handsome.shop.service.impl;

import com.handsome.shop.bean.Address;
import com.handsome.shop.dao.AddressDao;
import com.handsome.shop.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * by wangrongjun on 2018/4/17.
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    @Override
    public List<Address> getByCustomerId(int customerId) {
        return addressDao.queryByCustomerId(customerId);
    }

    @Override
    public boolean addAddress(Address address) {
        return addressDao.insert(address);
    }

}
