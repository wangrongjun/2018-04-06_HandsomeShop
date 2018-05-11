package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * by wangrongjun on 2018/5/10.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ancestorId", "descendantId"})})
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class GoodsTypeRelation extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer goodsTypeRelationId;
    @ManyToOne
    @JoinColumn(name = "ancestorId")
    private GoodsType ancestor;// 祖先
    @ManyToOne
    @JoinColumn(name = "descendantId")
    private GoodsType descendant;// 后代
    private Integer pathDepth;

    public GoodsTypeRelation() {
    }

    public GoodsTypeRelation(GoodsType ancestor, GoodsType descendant, Integer pathDepth) {
        this.ancestor = ancestor;
        this.descendant = descendant;
        this.pathDepth = pathDepth;
    }

    public Integer getGoodsTypeRelationId() {
        return goodsTypeRelationId;
    }

    public void setGoodsTypeRelationId(Integer goodsTypeRelationId) {
        this.goodsTypeRelationId = goodsTypeRelationId;
    }

    public GoodsType getAncestor() {
        return ancestor;
    }

    public void setAncestor(GoodsType ancestor) {
        this.ancestor = ancestor;
    }

    public GoodsType getDescendant() {
        return descendant;
    }

    public void setDescendant(GoodsType descendant) {
        this.descendant = descendant;
    }

    public Integer getPathDepth() {
        return pathDepth;
    }

    public void setPathDepth(Integer depth) {
        this.pathDepth = depth;
    }
}
