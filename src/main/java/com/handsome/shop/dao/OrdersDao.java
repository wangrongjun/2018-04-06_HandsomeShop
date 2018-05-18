package com.handsome.shop.dao;

import com.handsome.shop.entity.Orders;
import com.handsome.shop.framework.Dao;
import com.handsome.shop.util.Pager;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface OrdersDao extends Dao<Orders, Integer> {
    List<Orders> queryByCustomerId(int customerId, Pager<Orders> pager);

    int queryCountByCustomerId(int seller);

    List<Orders> queryCreatedBySellerId(int sellerId, Pager<Orders> pager);

    int queryCreatedCountBySellerId(int sellerId);

    void deleteLogically(int ordersId);
}
