package com.handsome.shop.dao.impl;

import com.handsome.shop.bean.Goods;
import com.handsome.shop.dao.GoodsDao;
import com.wangrj.java_lib.hibernate.HibernateDao;
import com.wangrj.java_lib.hibernate.Q;
import com.wangrj.java_lib.hibernate.Where;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public class GoodsDaoImpl extends HibernateDao<Goods> implements GoodsDao {

    private int begin = 0;
    private int count = 0;
    private int sortType = 0;

    private static String getOrderByWordFromSortType(int sortType) {
        switch (sortType) {
            case 0://综合排序
                return null;
            case 1://销量排序
                return null;
            case 2://价格由低到高
                return "price";
            case 3://价格由高到低
                return "-price";
        }
        return null;
    }

    @Override
    public GoodsDao begin(int begin) {
        this.begin = begin;
        return this;
    }

    @Override
    public GoodsDao count(int count) {
        this.count = count;
        return this;
    }

    @Override
    public GoodsDao sortType(int sortType) {
        this.sortType = sortType;
        return this;
    }

    @Override
    public int queryCountByShopId(int shopId) {
        return queryCount(Where.eq("shop.id", shopId));
    }

    @Override
    public List<Goods> queryByShopId(int shopId, int begin, int count) {
        Q q = Q.where(Where.eq("shop.id", shopId)).limit(begin, count);
        return query(q);
    }

    @Override
    public int queryCountBySearchWord(String searchWord) {
        Where where = new Where().like("goodsName", "%" + searchWord + "%");
        return queryCount(where);
    }

    @Override
    public List<Goods> queryBySearchWord(String searchWord, int begin, int count) {
        Where where = new Where().like("goodsName", "%" + searchWord + "%");
        return query(Q.where(where).limit(begin, count));
    }

    @Override
    public int queryCountByGoodsTypeId(int goodsTypeId) {
        return queryCount(Where.eq("goodsType.id", String.valueOf(goodsTypeId)));
    }

    @Override
    public List<Goods> queryByGoodsTypeId(int goodsTypeId, int begin, int count) {
        Where where = Where.eq("goodsType.id", String.valueOf(goodsTypeId));
        return query(Q.where(where).limit(begin, count));
    }

    @Override
    public List<Goods> queryAll() {
        String sortType = getOrderByWordFromSortType(this.sortType);
        return query(new Q().limit(begin, count).orderBy(sortType));
    }

    @Override
    public int queryCountByCustomerId(int customerId) {
        String hql = "select count(*) from Goods g " +
                "join ShopCar c " +
                "on c.goods=g " +
                "and c.customer.id=" + customerId;
        return executeQueryCount(hql);
    }

    @Override
    public List<Goods> queryByCustomerId(int customerId, int offset, int rowCount) {
        String hql = "select g from Goods g " +
                "join ShopCar c " +
                "on c.goods=g " +
                "and c.customer.id=" + customerId;
        return executeQuery(hql, offset, rowCount);
    }

}
