package com.handsome.shop.entity;


import com.handsome.shop.framework.BaseEntity;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/19.
 */
@Entity
public class Address extends BaseEntity {

    @Id
    @GeneratedValue
    private int addressId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    private String address;

    public Address() {
    }

    public Address(int addressId) {
        this.addressId = addressId;
    }

    public Address(Customer customer, String address) {
        this.customer = customer;
        this.address = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
