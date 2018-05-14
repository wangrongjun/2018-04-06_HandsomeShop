package com.handsome.shop.dao;

import com.handsome.shop.entity.ShopCar;
import com.handsome.shop.framework.Dao;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface ShopCarDao extends Dao<ShopCar, Integer> {

    long queryCountByCustomerId(int customerId);

}
