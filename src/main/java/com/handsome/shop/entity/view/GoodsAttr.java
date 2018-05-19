package com.handsome.shop.entity.view;

import java.util.List;

/**
 * by wangrongjun on 2018/5/19.
 */
public class GoodsAttr {

    private List<Attr> attrs;
    private Integer inventory;// 库存
    private Double price;

    public static class Attr {
        private String name;
        private String value;

        public Attr(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    public GoodsAttr(List<Attr> attrs, Integer inventory, Double price) {
        this.attrs = attrs;
        this.inventory = inventory;
        this.price = price;
    }

}
