package com.handsome.shop.dao.impl;

import com.handsome.shop.bean.Address;
import com.handsome.shop.dao.AddressDao;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by wangrongjun on 2017/6/19.
 */
@Repository
public class AddressDaoImpl extends HibernateDao<Address> implements AddressDao {

    @Override
    public List<Address> queryByCustomerId(int customerId) {
        return query(Where.eq("customer.id", customerId));
    }

}
