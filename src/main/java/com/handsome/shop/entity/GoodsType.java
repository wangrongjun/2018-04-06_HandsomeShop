package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * by wangrongjun on 2017/6/18.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class GoodsType extends BaseEntity {

    @Id
    @GeneratedValue
    private int goodsTypeId;
    @Column(nullable = false, unique = true)
    private String name;

    @Transient
    private List<GoodsType> children;

    @Override
    public String toString() {
        return "GoodsType{" +
                "goodsTypeId=" + goodsTypeId +
                ", name='" + name + '\'' +
                '}';
    }

    public GoodsType() {
    }

    public GoodsType(int goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public GoodsType(String name) {
        this.name = name;
    }

    public int getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(int goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsType> getChildren() {
        return children;
    }

    public void setChildren(List<GoodsType> children) {
        this.children = children;
    }
}
