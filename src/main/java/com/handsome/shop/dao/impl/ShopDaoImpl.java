package com.handsome.shop.dao.impl;

import com.handsome.shop.entity.Shop;
import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.framework.HibernateDao;
import org.springframework.stereotype.Repository;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
public class ShopDaoImpl extends HibernateDao<Shop> implements ShopDao {
}
