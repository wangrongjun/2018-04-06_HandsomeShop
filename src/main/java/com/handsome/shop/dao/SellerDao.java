package com.handsome.shop.dao;

import com.handsome.shop.bean.Seller;
import com.wangrj.java_lib.hibernate.Dao;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface SellerDao extends Dao<Seller> {

    Seller queryByPhone(String phone);

}
