package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
public class Shop extends BaseEntity {

    @Id
    @GeneratedValue
    private int shopId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;
    private String shopName;
    private String description;
    private String headUrl;

    public Shop() {
    }

    public Shop(int shopId) {
        this.shopId = shopId;
    }

    public Shop(Seller seller, String shopName, String description, String headUrl) {
        this.seller = seller;
        this.shopName = shopName;
        this.description = description;
        this.headUrl = headUrl;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", seller=" + seller +
                ", shopName='" + shopName + '\'' +
                ", description='" + description + '\'' +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
