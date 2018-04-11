package com.handsome.shop.dao.impl;

import com.handsome.shop.bean.Seller;
import com.handsome.shop.dao.SellerDao;
import com.wangrj.java_lib.hibernate.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public class SellerDaoImpl extends HibernateDao<Seller> implements SellerDao {

    @Override
    public Seller queryByPhone(String phone) {
        List<Seller> list = query(Where.eq("phone", phone));
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
