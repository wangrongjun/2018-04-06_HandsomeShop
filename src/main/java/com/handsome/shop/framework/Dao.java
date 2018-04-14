package com.handsome.shop.framework;

import com.wangrj.java_lib.hibernate.Q;
import com.wangrj.java_lib.hibernate.Where;

import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
public interface Dao<T> {

    boolean insert(T entity);

    default boolean insertBatch(List<T> entityList) {
        return false;
    }

    boolean delete(Where where);

    default boolean deleteBatchByIdList(List<Long> idList) {
        return false;
    }

    boolean deleteById(long id);

    default boolean deleteAll() {
        return false;
    }

    boolean update(T entity);

    default boolean updateBatch(List<T> entityList) {
        return false;
    }

    T queryById(long id);

    List<T> queryAll();

    List<T> query(Where where);

    List<T> query(Q q);

    int queryCount(Where where);

}

