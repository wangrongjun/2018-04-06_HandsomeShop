package com.handsome.shop.bean;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
public class GoodsImage {

    @Id
    @GeneratedValue
    private int imageId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;//外键，该图片所属的商品。查询时不必给该变量赋值。
    private String imageUrl;

    public GoodsImage() {
    }

    public GoodsImage(int imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public GoodsImage(Goods goods, String imageUrl) {
        this.goods = goods;
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
