package com.handsome.shop.dao;

import com.handsome.shop.entity.Goods;
import com.handsome.shop.framework.Dao;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface GoodsDao extends Dao<Goods, Integer> {

    GoodsDao begin(int begin);

    GoodsDao count(int count);

    GoodsDao sortType(int sortType);

    /**
     * 查询某个商店下的所有商品的数量，用于分页
     */
    int queryCountByShopId(int shopId);

    /**
     * 查询某个商店下的所有商品
     */
    List<Goods> queryByShopId(int shopId, int begin, int count);

    /**
     * 按照搜索关键字分页查询到的记录数，用于分页
     */
    int queryCountBySearchWord(String searchWord);

    /**
     * 按照搜索关键字分页查询，用户搜索商品时使用该方法
     */
    List<Goods> queryBySearchWord(String searchWord, int begin, int count);

    /**
     * 按照商品类型id查询到的记录数，用于分页
     */
    int queryCountByGoodsTypeId(int goodsTypeId);

    /**
     * 按照按照商品类型id分页查询，用户按照商品类型搜索商品时使用该方法
     */
    List<Goods> queryByGoodsTypeId(int goodsTypeId, int begin, int count);

    /**
     * 查询某个用户的购物车下的所有商品的数量，用于分页
     */
    int queryCountByCustomerId(int customerId);

    /**
     * 查询某个用户的购物车下的所有商品
     */
    List<Goods> queryByCustomerId(int customerId, int begin, int count);
}
