package com.handsome.shop.dao.impl;

import com.handsome.shop.entity.ShopCar;
import com.handsome.shop.dao.ShopCarDao;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

/**
 * by wangrongjun on 2017/6/16.
 */
@Repository
public class ShopCarDaoImpl extends HibernateDao<ShopCar, Integer> implements ShopCarDao {

    @Override
    public long queryCountByCustomerId(int customerId) {
        return queryCount(Where.eq("customer", customerId + ""));
    }

}
