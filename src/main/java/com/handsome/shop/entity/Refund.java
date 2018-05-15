package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * by wangrongjun on 2018/5/15.
 */
@Entity
public class Refund extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer refundId;
    @OneToOne(fetch = FetchType.LAZY)
    private Orders orders;
    private String reason;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "refundId"), inverseJoinColumns = @JoinColumn(name = "pictureId"))
    private Set<Picture> pictureSet;

    public Refund() {
    }

    public Refund(Integer refundId) {
        this.refundId = refundId;
    }

    public Refund(Orders orders, String reason, Set<Picture> pictureSet) {
        this.orders = orders;
        this.reason = reason;
        this.pictureSet = pictureSet;
    }

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Set<Picture> getPictureSet() {
        return pictureSet;
    }

    public void setPictureSet(Set<Picture> pictureList) {
        this.pictureSet = pictureList;
    }
}
