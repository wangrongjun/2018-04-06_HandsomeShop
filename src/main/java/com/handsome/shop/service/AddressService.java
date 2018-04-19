package com.handsome.shop.service;

import com.handsome.shop.bean.Address;

import java.util.List;

/**
 * by wangrongjun on 2018/4/17.
 */
public interface AddressService {

    List<Address> getByCustomerId(int customerId);

    boolean addAddress(Address address);

}
