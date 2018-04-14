package com.handsome.shop.dao;

import com.handsome.shop.bean.Orders;
import com.handsome.shop.framework.Dao;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface OrdersDao extends Dao<Orders> {
    List<Orders> queryByCustomerId(int customerId);

    int queryCountByCustomerId(int customerId);

    int queryCountByGoodsId(int goodsId);
}
