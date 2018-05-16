package com.handsome.shop.dao;

import com.handsome.shop.entity.Shop;
import com.handsome.shop.framework.Dao;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface ShopDao extends Dao<Shop, Integer> {

    List<Shop> queryBySellerId(int sellerId);//查询某个卖家的所有商店

}
