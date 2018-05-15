package com.handsome.shop.dao.impl;

import com.handsome.shop.dao.RefundDao;
import com.handsome.shop.entity.Refund;
import com.handsome.shop.framework.HibernateDao;
import org.springframework.stereotype.Repository;

/**
 * by wangrongjun on 2018/5/15.
 */
@Repository
public class RefundDaoImpl extends HibernateDao<Refund, Integer> implements RefundDao {

}
