package com.handsome.shop.dao;

import com.handsome.shop.entity.GoodsType;
import com.handsome.shop.entity.GoodsTypeRelation;
import com.handsome.shop.framework.Dao;
import com.wangrj.java_lib.hibernate.Where;

/**
 * by wangrongjun on 2018/5/10.
 */
public interface GoodsTypeRelationDao extends Dao<GoodsTypeRelation> {

    void addRelation(GoodsType parentType, GoodsType newType);

    GoodsType queryWithChildren(Where where);

}
