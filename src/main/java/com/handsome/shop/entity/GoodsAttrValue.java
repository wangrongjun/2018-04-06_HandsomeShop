package com.handsome.shop.entity;

import javax.persistence.*;

/**
 * by wangrongjun on 2018/5/19.
 */
@Entity
public class GoodsAttrValue {

    @Id
    @GeneratedValue
    private Integer goodsAttrValueId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsAttrNameId")
    private GoodsAttrName goodsAttrName;
    private String value;

    public GoodsAttrValue() {
    }

    public GoodsAttrValue(Integer goodsAttrValueId) {
        this.goodsAttrValueId = goodsAttrValueId;
    }

    public GoodsAttrValue(GoodsAttrName goodsAttrName, String value) {
        this.goodsAttrName = goodsAttrName;
        this.value = value;
    }

    public Integer getGoodsAttrValueId() {
        return goodsAttrValueId;
    }

    public void setGoodsAttrValueId(Integer goodsAttrValueId) {
        this.goodsAttrValueId = goodsAttrValueId;
    }

    public GoodsAttrName getGoodsAttrName() {
        return goodsAttrName;
    }

    public void setGoodsAttrName(GoodsAttrName goodsAttrName) {
        this.goodsAttrName = goodsAttrName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
