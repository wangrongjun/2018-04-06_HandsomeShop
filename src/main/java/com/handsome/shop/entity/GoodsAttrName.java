package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * by wangrongjun on 2018/5/15.
 */
@Entity
public class GoodsAttrName extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer goodsAttrNameId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsId")
    private Goods goods;
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "goodsAttrNameId")
    @OrderBy("goodsAttrValueId")
    private Set<GoodsAttrValue> attrValues;

    public GoodsAttrName() {
    }

    public GoodsAttrName(Integer goodsAttrNameId) {
        this.goodsAttrNameId = goodsAttrNameId;
    }

    public GoodsAttrName(Goods goods, String name) {
        this.goods = goods;
        this.name = name;
    }

    public Integer getGoodsAttrNameId() {
        return goodsAttrNameId;
    }

    public void setGoodsAttrNameId(Integer goodsAttrNameId) {
        this.goodsAttrNameId = goodsAttrNameId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GoodsAttrValue> getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(Set<GoodsAttrValue> attrValues) {
        this.attrValues = attrValues;
    }
}
