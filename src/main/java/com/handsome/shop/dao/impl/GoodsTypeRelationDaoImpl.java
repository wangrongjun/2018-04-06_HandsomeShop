package com.handsome.shop.dao.impl;

import com.handsome.shop.dao.GoodsTypeRelationDao;
import com.handsome.shop.entity.GoodsType;
import com.handsome.shop.entity.GoodsTypeRelation;
import com.handsome.shop.framework.HibernateDao;
import com.wangrj.java_lib.hibernate.Where;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by wangrongjun on 2018/5/10.
 */
@Repository
public class GoodsTypeRelationDaoImpl extends HibernateDao<GoodsTypeRelation, Integer> implements GoodsTypeRelationDao {

    /**
     * 添加的策略：newType 先加自己与父母及父母祖先的关系，再加自己与自己的关系
     */
    @Override
    public void addRelation(GoodsType parentType, GoodsType newType) {

        Where where = new Where().
                equal("ancestorId", parentType.getGoodsTypeId()).
                equal("descendantId", parentType.getGoodsTypeId());
        List<GoodsTypeRelation> list = query(where);
        // 如果父母没有自己到自己的关联，就先添加
        if (list.size() == 0) {
            insert(new GoodsTypeRelation(parentType, parentType, 0));
        }

        List<GoodsTypeRelation> ancestorList = query(Where.eq("descendantId", parentType.getGoodsTypeId()));
        for (GoodsTypeRelation ancestor : ancestorList) {
            insert(new GoodsTypeRelation(ancestor.getAncestor(), newType, ancestor.getPathDepth() + 1));
        }

        insert(new GoodsTypeRelation(newType, newType, 0));
    }

    @Override
    public GoodsType queryWithChildren(Where where) {
        return null;
    }

}
