package com.handsome.shop.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
public class ShopCar {

    @Id
    @GeneratedValue
    private int carId;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Goods goods;

    public ShopCar() {
    }

    public ShopCar(Customer customer, Goods goods) {
        this.customer = customer;
        this.goods = goods;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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
}
