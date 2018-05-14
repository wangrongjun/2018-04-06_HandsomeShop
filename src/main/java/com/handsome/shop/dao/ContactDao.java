package com.handsome.shop.dao;

import com.handsome.shop.entity.Contact;
import com.handsome.shop.framework.Dao;

import java.util.List;

/**
 * by wangrongjun on 2017/6/19.
 */
public interface ContactDao extends Dao<Contact, Integer> {

    List<Contact> queryByCustomerId(int customerId);

    /**
     * 设置该用户的所有Contact记录的defaultContact字段为0（false）
     */
    void setDefaultContact(int customerId, int defaultContactId);

}
