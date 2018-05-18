package com.handsome.shop.dao.impl;

import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.entity.Orders;
import com.handsome.shop.framework.HibernateDao;
import com.handsome.shop.util.Pager;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
public class OrdersDaoImpl extends HibernateDao<Orders, Integer> implements OrdersDao {

    @Override
    public List<Orders> queryByCustomerId(int customerId, Pager<Orders> pager) {
        return query(Where.eq("customer.id", customerId).isNull("obsoleteDate"), pager);
    }

    @Override
    public int queryCountByCustomerId(int customerId) {
        return queryCount(Where.eq("customer.id", customerId).isNull("obsoleteDate"));
    }

    @Override
    public List<Orders> queryCreatedBySellerId(int sellerId, Pager<Orders> pager) {
        Where where = Where.
                eq("goods.shop.seller.id", sellerId).
                notEqual("status", Orders.Status.Closed.toString()).
                notEqual("status", Orders.Status.Finish.toString()).
                isNull("obsoleteDate");
        return query(where, pager);
    }

    @Override
    public int queryCreatedCountBySellerId(int sellerId) {
        Where where = Where.
                eq("goods.shop.seller.id", sellerId).
                notEqual("status", Orders.Status.Closed.toString()).
                notEqual("status", Orders.Status.Finish.toString()).
                isNull("obsoleteDate");
        return queryCount(where);
    }

    // TODO 在HibernateDao中实现
    @Override
    public void deleteLogically(int ordersId) {
        Orders orders = queryById(ordersId);
        orders.setObsoleteDate(new Date());
        update(orders);
    }

}
