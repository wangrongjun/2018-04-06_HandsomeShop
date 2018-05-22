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
public class Shop extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer shopId;
    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Seller seller;
    private String shopName;
    private String description;
    @ManyToOne
    @JoinColumn(name = "headId")
    private Picture head;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "shopId")
    private Set<Goods> goodsSet;

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", seller=" + seller +
                ", shopName='" + shopName + '\'' +
                ", description='" + description + '\'' +
                ", head=" + head +
                '}';
    }

    public Shop() {
    }

    public Shop(Integer shopId) {
        this.shopId = shopId;
    }

    public Shop(Seller seller, String shopName, String description, Picture head) {
        this.seller = seller;
        this.shopName = shopName;
        this.description = description;
        this.head = head;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Picture getHead() {
        return head;
    }

    public void setHead(Picture head) {
        this.head = head;
    }

    public Set<Goods> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<Goods> goodsSet) {
        this.goodsSet = goodsSet;
    }
}
