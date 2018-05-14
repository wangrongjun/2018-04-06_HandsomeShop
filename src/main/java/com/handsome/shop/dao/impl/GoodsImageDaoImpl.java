package com.handsome.shop.dao.impl;

import com.handsome.shop.entity.GoodsImage;
import com.handsome.shop.dao.GoodsImageDao;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
public class GoodsImageDaoImpl extends HibernateDao<GoodsImage, Integer> implements GoodsImageDao {

    @Override
    public List<GoodsImage> queryByGoodsId(int goodsId) {
        return query(Where.eq("goods.id", String.valueOf(goodsId)));
    }

}
