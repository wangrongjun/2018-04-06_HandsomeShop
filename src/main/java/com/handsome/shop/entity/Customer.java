package com.handsome.shop.entity;


import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue
    private int customerId;
    private String phone;
    private String password;
    private String realName;
    private String nickname;
    @Check(constraints = "gender IS NULL OR gender = '男' OR gender = '女'")
    private String gender;
    @ManyToOne
    @JoinColumn(name = "headId")
    private Picture head;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", head=" + head +
                '}';
    }

    public Customer() {
    }

    public Customer(int customerId) {
        this.customerId = customerId;
    }

    public Customer(String phone, String password, String realName, String nickname, String gender, Picture head) {
        this.phone = phone;
        this.password = password;
        this.realName = realName;
        this.nickname = nickname;
        this.gender = gender;
        this.head = head;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
}
