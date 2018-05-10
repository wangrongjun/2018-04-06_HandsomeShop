package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue
    private int sellerId;
    private String phone;
    private String password;
    private String realName;
    private String nickname;
    @Check(constraints = "gender IS NULL OR gender = '男' OR gender = '女'")
    private String gender;
    @ManyToOne
    @JoinColumn(name = "headId")
    private Picture head;
    @OneToMany
    @JoinColumn(name = "sellerId")
    private List<Shop> shopList;

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", head=" + head +
                ", shopList=" + shopList +
                '}';
    }

    public Seller() {
    }

    public Seller(int sellerId) {
        this.sellerId = sellerId;
    }

    public Seller(String phone, String password, String realName, String nickname, String gender, Picture head) {
        this.phone = phone;
        this.password = password;
        this.realName = realName;
        this.nickname = nickname;
        this.gender = gender;
        this.head = head;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Picture getHead() {
        return head;
    }

    public void setHead(Picture head) {
        this.head = head;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
