package com.handsome.shop.domain;

/**
 * by wangrongjun on 2017/9/29.
 */
public class GenderReport {

    private int gender;
    private long count;

    public GenderReport() {
    }

    public GenderReport(int gender, long count) {
        this.gender = gender;
        this.count = count;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
