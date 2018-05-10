package com.handsome.shop.entity;


import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class Orders extends BaseEntity {

    // TODO 订单应该有商品名称，下单时的价格，而且goods可为空，代表商品下架

    @Transient
    public static final int STATE_CONTINUE = 0;
    @Transient
    public static final int STATE_SUCCEED = 1;
    @Transient
    public static final int STATE_FAILED = 2;

    @Id
    @GeneratedValue
    private int ordersId;
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
    private Date createTime;//订单创建时间，格式：”yyyy-MM-dd HH:mm:ss”
    private int state;//订单状态，进行中，关闭，成功 TODO 改为enum类型

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", customer=" + customer +
                ", goods=" + goods +
                ", buyCount=" + buyCount +
                ", price=" + price +
                ", contact=" + contact +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                '}';
    }

    public Orders() {
    }

    public Orders(int ordersId) {
        this.ordersId = ordersId;
    }

    public Orders(Customer customer, Goods goods, int buyCount, double price, Contact contact, Date createTime, int state) {
        this.customer = customer;
        this.goods = goods;
        this.buyCount = buyCount;
        this.price = price;
        this.contact = contact;
        this.createTime = createTime;
        this.state = state;
    }

    public static int getStateContinue() {
        return STATE_CONTINUE;
    }

    public static int getStateSucceed() {
        return STATE_SUCCEED;
    }

    public static int getStateFailed() {
        return STATE_FAILED;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
