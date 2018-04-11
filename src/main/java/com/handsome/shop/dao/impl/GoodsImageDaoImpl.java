package com.handsome.shop.dao.impl;

import com.handsome.shop.bean.GoodsImage;
import com.handsome.shop.dao.GoodsImageDao;
import com.wangrj.java_lib.hibernate.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public class GoodsImageDaoImpl extends HibernateDao<GoodsImage> implements GoodsImageDao {

    @Override
    public List<GoodsImage> queryByGoodsId(int goodsId) {
        return query(Where.eq("goods.id", String.valueOf(goodsId)));
    }

}
