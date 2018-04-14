package com.handsome.shop.dao;

import com.handsome.shop.bean.ShopCar;
import com.handsome.shop.framework.Dao;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface ShopCarDao extends Dao<ShopCar> {

    long queryCountByCustomerId(int customerId);

}
