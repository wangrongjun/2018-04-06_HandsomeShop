package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class Shop extends BaseEntity {

    @Id
    @GeneratedValue
    private int shopId;
    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Seller seller;
    private String shopName;
    private String description;
    @ManyToOne
    private Picture head;

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

    public Shop(int shopId) {
        this.shopId = shopId;
    }

    public Shop(Seller seller, String shopName, String description, Picture head) {
        this.seller = seller;
        this.shopName = shopName;
        this.description = description;
        this.head = head;
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

    public Picture getHead() {
        return head;
    }

    public void setHead(Picture head) {
        this.head = head;
    }
}
