package com.handsome.shop.dao.impl;

import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.entity.Orders;
import com.handsome.shop.framework.HibernateDao;
import com.handsome.shop.util.Pager;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
public class OrdersDaoImpl extends HibernateDao<Orders> implements OrdersDao {

    @Override
    public List<Orders> queryByCustomerId(int customerId, Pager<Orders> pager) {
        CriteriaQuery<Orders> query = getSession().getCriteriaBuilder().createQuery(Orders.class);
        return query(Where.eq("customer.id", customerId), pager);
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
