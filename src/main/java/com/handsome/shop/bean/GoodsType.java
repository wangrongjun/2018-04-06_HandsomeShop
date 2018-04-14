package com.handsome.shop.bean;

import com.handsome.shop.framework.BaseEntity;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/18.
 */
@Entity
public class GoodsType extends BaseEntity {

    @Id
    @GeneratedValue
    private int goodsTypeId;
    @Column(nullable = false, unique = true)
    private String name;

    public GoodsType() {
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "goodsTypeId=" + goodsTypeId +
                ", name='" + name + '\'' +
                '}';
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
}
