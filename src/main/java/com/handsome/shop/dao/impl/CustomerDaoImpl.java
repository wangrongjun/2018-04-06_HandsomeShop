package com.handsome.shop.dao.impl;

import com.handsome.shop.entity.Customer;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * by wangrongjun on 2017/6/17.
 */
@Repository
public class CustomerDaoImpl extends HibernateDao<Customer> implements CustomerDao {

    @Override
    public Customer queryByPhone(String phone) {
        List<Customer> list = query(Where.eq("phone", phone));
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean updateHeadUrl(int customerId, String headUrl) {
        Customer customer = queryById(customerId);
        customer.setHeadUrl(headUrl);
        return update(customer);
    }

    @Override
    public List<Map<String, Object>> countGender() {
        Session session = getSession();
        String hql = "select gender,count(customerId) from Customer group by gender";
        Query query = session.createQuery(hql);
        List list = query.list();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Object o : list) {
            Object[] obj = (Object[]) o;
            int gender = (int) obj[0];
            long count = (long) obj[1];
            Map<String, Object> map = new HashMap<>();
            map.put("gender", gender);
            map.put("count", count);
            mapList.add(map);
        }
        return mapList;
    }

}
