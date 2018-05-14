package com.handsome.shop.dao;

import com.handsome.shop.entity.Seller;
import com.handsome.shop.framework.Dao;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface SellerDao extends Dao<Seller, Integer> {

    Seller queryByPhone(String phone);

}
