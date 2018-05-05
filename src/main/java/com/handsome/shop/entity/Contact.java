package com.handsome.shop.entity;


import com.handsome.shop.framework.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * by wangrongjun on 2017/6/19.
 */
@Entity
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue
    private int contactId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @NotNull
    private String phone;// 收货人电话
    @NotNull
    private String receiver;// 收货人
    @NotNull
    private String address;// 收货人地址
    private boolean defaultContact;// 默认收货地址

    public Contact() {
    }

    public Contact(int contactId) {
        this.contactId = contactId;
    }

    public Contact(Customer customer, String phone, String receiver, String address, boolean defaultContact) {
        this.customer = customer;
        this.phone = phone;
        this.receiver = receiver;
        this.address = address;
        this.defaultContact = defaultContact;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDefaultContact() {
        return defaultContact;
    }

    public void setDefaultContact(boolean defaultContact) {
        this.defaultContact = defaultContact;
    }
}
