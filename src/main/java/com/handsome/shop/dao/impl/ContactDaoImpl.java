package com.handsome.shop.dao.impl;

import com.handsome.shop.entity.Contact;
import com.handsome.shop.dao.ContactDao;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Q;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by wangrongjun on 2017/6/19.
 */
@Repository
public class ContactDaoImpl extends HibernateDao<Contact, Integer> implements ContactDao {

    @Override
    public List<Contact> queryByCustomerId(int customerId) {
        return query(Q.where(Where.eq("customer.id", customerId)).orderBy("-defaultContact"));
    }

    @Override
    public void setDefaultContact(int customerId, int defaultContactId) {
        String hql = "update Contact set defaultContact=0 where customer.customerId=?";
        executeUpdate(hql, customerId);
        hql = "update Contact set defaultContact=1 where customer.customerId=? and defaultContact=?";
        executeUpdate(hql, customerId);
    }

}
