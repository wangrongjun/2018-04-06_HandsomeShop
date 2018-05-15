package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * by wangrongjun on 2018/5/15.
 */
@Entity
public class GoodsAttr extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer goodsAttrId;
    @ManyToOne
    private Goods goods;
    private String attrName;
    private String attrValue;
    private Integer remainCount;// 库存

    public GoodsAttr() {
    }

    public GoodsAttr(Integer goodsAttrId) {
        this.goodsAttrId = goodsAttrId;
    }
}
