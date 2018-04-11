package com.handsome.shop.dao.impl;

import com.handsome.shop.bean.ShopCar;
import com.handsome.shop.dao.ShopCarDao;
import com.wangrj.java_lib.hibernate.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;

/**
 * by wangrongjun on 2017/6/16.
 */
public class ShopCarDaoImpl extends HibernateDao<ShopCar> implements ShopCarDao {

    @Override
    public long queryCountByCustomerId(int customerId) {
        return queryCount(Where.eq("customer", customerId + ""));
    }

}
