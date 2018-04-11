package com.handsome.shop.dao;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.domain.GenderReport;
import com.wangrj.java_lib.hibernate.Dao;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface CustomerDao extends Dao<Customer> {

    Customer queryByPhone(String phone);

    boolean updateHeadUrl(int customerId, String headUrl);

    List<GenderReport> countGender();

}
