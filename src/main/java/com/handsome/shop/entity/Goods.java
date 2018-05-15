package com.handsome.shop.entity;


import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class Goods extends BaseEntity {

    // TODO 加一个字段：firstImage，那么在查询商品列表时，就不用为每个商品查询图片列表。适当的冗余可以极大地提高效率。
    // TODO 加一个字段：sellCount，那么在查询商品列表时，就不用为每个商品查询总销量。适当的冗余可以极大地提高效率。

    @Id
    @GeneratedValue
    private Integer goodsId;
    private String goodsName;
    private String description;
    private double price;
    private int remainCount;// 库存
    @ManyToOne
    @JoinColumn(name = "goodsTypeId")
    private GoodsType goodsType;
    @ManyToOne
    @JoinColumn(name = "shopId")
    private Shop shop;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "goodsId"), inverseJoinColumns = @JoinColumn(name = "pictureId"))
    @OrderBy("pictureId")
    private Set<Picture> pictureSet;

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", remainCount=" + remainCount +
                ", goodsType=" + goodsType +
                ", shop=" + shop +
                ", pictureSet=" + pictureSet +
                '}';
    }

    public Goods() {
    }

    public Goods(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Goods(String goodsName, String description, double price, int remainCount, GoodsType goodsType, Shop shop, Set<Picture> pictureSet) {
        this.goodsName = goodsName;
        this.description = description;
        this.price = price;
        this.remainCount = remainCount;
        this.goodsType = goodsType;
        this.shop = shop;
        this.pictureSet = pictureSet;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(int remainCount) {
        this.remainCount = remainCount;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Set<Picture> getPictureSet() {
        return pictureSet;
    }

    public void setPictureSet(Set<Picture> pictureList) {
        this.pictureSet = pictureList;
    }
}
