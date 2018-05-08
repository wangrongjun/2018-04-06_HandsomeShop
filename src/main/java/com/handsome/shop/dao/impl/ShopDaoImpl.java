package com.handsome.shop.dao.impl;

import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
public class ShopDaoImpl extends HibernateDao<Shop> implements ShopDao {

    @Override
    public List<Shop> queryBySellerId(int sellerId) {
        return query(Where.eq("seller.id", sellerId));
    }

}
