package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class GoodsImage extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer goodsImageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsId")
    private Goods goods;//外键，该图片所属的商品。查询时不必给该变量赋值。
    private String imageUrl;

    @Override
    public String toString() {
        return "GoodsImage{" +
                "imageId=" + goodsImageId +
                ", goods=" + goods +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public GoodsImage() {
    }

    public GoodsImage(Integer goodsImageId, String imageUrl) {
        this.goodsImageId = goodsImageId;
        this.imageUrl = imageUrl;
    }

    public GoodsImage(Goods goods, String imageUrl) {
        this.goods = goods;
        this.imageUrl = imageUrl;
    }

    public Integer getGoodsImageId() {
        return goodsImageId;
    }

    public void setGoodsImageId(Integer imageId) {
        this.goodsImageId = imageId;
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
