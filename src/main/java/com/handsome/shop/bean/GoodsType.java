package com.handsome.shop.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * by wangrongjun on 2017/6/18.
 */
@Entity
public class GoodsType {

    @Id
    @GeneratedValue
    private int goodsTypeId;
    @Column(nullable = false, unique = true)
    private String name;

    public GoodsType() {
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
