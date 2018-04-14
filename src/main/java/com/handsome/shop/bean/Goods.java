package com.handsome.shop.bean;


import com.handsome.shop.framework.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
public class Goods extends BaseEntity {

    // TODO 加一个字段：firstImage，那么在查询商品列表时，就不用为每个商品查询图片列表。适当的冗余可以极大地提高效率。
    // TODO 加一个字段：sellCount，那么在查询商品列表时，就不用为每个商品查询总销量。适当的冗余可以极大地提高效率。

    @Transient
    private int sellCount;

    @Id
    @GeneratedValue
    private int goodsId;
    private String goodsName;
    private String description;
    private int remainCount;// 库存
    @ManyToOne(fetch = FetchType.LAZY)
    private GoodsType goodsType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;//Shop外键，所属的商店
    @OneToMany
    @JoinColumn(name = "goodsId")
    private List<GoodsImage> goodsImageList;
    private double price;

    @Override
    public String toString() {
        return "Goods{" +
                "sellCount=" + sellCount +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", description='" + description + '\'' +
                ", remainCount=" + remainCount +
                ", goodsType=" + goodsType +
                ", shop=" + shop +
                ", price=" + price +
                '}';
    }

    public Goods() {
    }

    public Goods(int goodsId) {
        this.goodsId = goodsId;
    }

    public Goods(String goodsName, String description, int remainCount, GoodsType goodsType, double price) {
        this.goodsName = goodsName;
        this.description = description;
        this.remainCount = remainCount;
        this.goodsType = goodsType;
        this.price = price;
    }

    public Goods(String goodsName, String description, int remainCount, GoodsType goodsType, Shop shop, double price) {
        this.goodsName = goodsName;
        this.description = description;
        this.remainCount = remainCount;
        this.goodsType = goodsType;
        this.shop = shop;
        this.price = price;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<GoodsImage> getGoodsImageList() {
        return goodsImageList;
    }

    public void setGoodsImageList(List<GoodsImage> goodsImageList) {
        this.goodsImageList = goodsImageList;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public int getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(int remainCount) {
        this.remainCount = remainCount;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

}
