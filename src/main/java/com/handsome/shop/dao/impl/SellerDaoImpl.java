package com.handsome.shop.dao.impl;

import com.handsome.shop.entity.Seller;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
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
