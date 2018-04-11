package com.handsome.shop.dao.impl;

import com.handsome.shop.bean.Orders;
import com.handsome.shop.dao.OrdersDao;
import com.wangrj.java_lib.hibernate.HibernateDao;
import com.wangrj.java_lib.hibernate.Q;
import com.wangrj.java_lib.hibernate.Where;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public class OrdersDaoImpl extends HibernateDao<Orders> implements OrdersDao {

    @Override
    public List<Orders> queryByCustomerId(int customerId) {
        return query(Q.where(Where.eq("customer.id", customerId)).orderBy("-createTime"));
    }

    @Override
    public int queryCountByCustomerId(int customerId) {
        return queryCount(Where.eq("customer.id", customerId));
    }

    @Override
    public int queryCountByGoodsId(int goodsId) {
        return queryCount(Where.eq("goods.id", goodsId));
    }

}
