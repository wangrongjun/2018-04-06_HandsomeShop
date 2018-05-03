package com.handsome.shop.dao.impl;

import com.handsome.shop.entity.Orders;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Q;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
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
