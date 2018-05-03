package com.handsome.shop.entity;


import com.handsome.shop.framework.BaseEntity;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @ManyToOne
    private Goods goods;
    private int buyCount;//该商品购买的数量
    private double price;//订单创建时商品的总价格（预防下单后商家修改该商品的价格导致出错）
    private String phone;//收货人的联系电话
    private String receiverName;//收货人的姓名
    private String address;//收货人的收货地址（预防下单后客户修改收货地址导致出错）
    private String createTime;//订单创建时间，格式：”yyyy-MM-dd HH:mm:ss”
    private int state;//订单状态，进行中，关闭，成功
//    private Contact contact;// 订单所对应的

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", customer=" + customer +
                ", goods=" + goods +
                ", buyCount=" + buyCount +
                ", price=" + price +
                ", phone='" + phone + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", address='" + address + '\'' +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                '}';
    }

    public Orders() {
    }

    public Orders(int ordersId) {
        this.ordersId = ordersId;
    }

    public Orders(Customer customer, Goods goods, int buyCount, double price, String phone,
                  String receiverName, String address, String createTime, int state) {
        this.customer = customer;
        this.goods = goods;
        this.buyCount = buyCount;
        this.price = price;
        this.phone = phone;
        this.receiverName = receiverName;
        this.address = address;
        this.createTime = createTime;
        this.state = state;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String realName) {
        this.receiverName = receiverName;
    }
}
