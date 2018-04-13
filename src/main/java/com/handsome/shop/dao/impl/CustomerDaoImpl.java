package com.handsome.shop.dao.impl;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.domain.GenderReport;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    public List<GenderReport> countGender() {
        Session session = openSession();
        String hql = "select gender,count(customerId) from Customer group by gender";
        Query query = session.createQuery(hql);
        List list = query.list();

        List<GenderReport> genderReportList = new ArrayList<>();
        for (Object o : list) {
            Object[] obj = (Object[]) o;
            int gender = (int) obj[0];
            long count = (long) obj[1];
            genderReportList.add(new GenderReport(gender, count));
        }

        closeSession();
        return genderReportList;
    }

}
