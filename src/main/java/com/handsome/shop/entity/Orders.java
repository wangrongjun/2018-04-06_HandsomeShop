package com.handsome.shop.entity;


import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class Orders extends BaseEntity {

    public enum Status {
        /**
         * 订单已创建，等待商家发货
         */
        Created(0),
        /**
         * 商家已发货，顾客等待收货
         */
        Pending_Receive(1),
        /**
         * 顾客已经收货
         */
        Received(2),
        /**
         * 顾客申请退款，等待商家退款
         */
        Pending_Refund(3),
        /**
         * 订单关闭
         */
        Closed(4),
        /**
         * 订单完成
         */
        Finish(5);

        int code;

        Status(int code) {
            this.code = code;
        }
    }

    @Id
    @GeneratedValue
    private Integer ordersId;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "goodsId")
    private Goods goods;
    private int buyCount;//该商品购买的数量
    private double price;//订单创建时商品的总价格（预防下单后商家修改该商品的价格导致出错）
    @ManyToOne
    @JoinColumn(name = "contactId")
    private Contact contact;// 订单所对应的收货地址
    private String remark;
    @Enumerated(EnumType.STRING)
    private Status status;//订单状态
    @OneToOne(mappedBy = "orders")
    private Refund refund;

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", customer=" + customer +
                ", goods=" + goods +
                ", buyCount=" + buyCount +
                ", price=" + price +
                ", contact=" + contact +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", refund='" + refund + '\'' +
                '}';
    }

    public Orders() {
    }

    public Orders(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Orders(Customer customer, Goods goods, int buyCount, double price, Contact contact, String remark, Status status) {
        this.customer = customer;
        this.goods = goods;
        this.buyCount = buyCount;
        this.price = price;
        this.contact = contact;
        this.remark = remark;
        this.status = status;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }
}
