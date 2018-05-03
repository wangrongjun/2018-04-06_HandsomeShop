package com.handsome.shop.framework;

import com.handsome.shop.util.Pager;
import com.wangrj.java_lib.hibernate.Q;
import com.wangrj.java_lib.hibernate.Where;

import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
public interface Dao<T> {

    boolean insert(T entity);

    boolean delete(Where where);

    boolean deleteAll();

    boolean deleteById(long id);

    boolean update(T entity);

    T queryById(long id);

    List<T> queryAll();

    List<T> query(Where where);

    List<T> query(Where where, Pager<T> pager);

    List<T> query(Q q);

    int queryCount(Where where);

}
