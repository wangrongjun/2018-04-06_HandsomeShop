package com.handsome.shop.dao.impl;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Evaluate;
import com.handsome.shop.bean.Orders;
import com.handsome.shop.dao.EvaluateDao;
import com.handsome.shop.framework.HibernateDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
public class EvaluateDaoImpl extends HibernateDao<Evaluate> implements EvaluateDao {

    @Override
    public List<Evaluate> queryByGoodsId(int goodsId) {
        String hql = "from Evaluate where orders.goods.id=" + goodsId;
        Session session = getSession();
        Query<Evaluate> query = session.createQuery(hql, Evaluate.class);
        return query.list();

//        List<Evaluate> evaluateList = new ArrayList<>();
//        String hql = "select e,c from Evaluate e " +
//                "join Customer c on e.orders.customer=c " +
//                "where e.orders.goods.id=" + goodsId;
//        Session session = getSession();
//        Query query = session.createQuery(hql);
//        List<Object[]> list = query.list();
//        closeSession();
//        for (Object[] objects : list) {
//            Evaluate evaluate = (Evaluate) objects[0];
//            Customer customer = (Customer) objects[1];
//            customer.setPassword(null);
//            Orders orders = new Orders();
//            orders.setCustomer(customer);
//            evaluate.setOrders(orders);
//            evaluateList.add(evaluate);
//        }
//        return evaluateList;
    }

}
