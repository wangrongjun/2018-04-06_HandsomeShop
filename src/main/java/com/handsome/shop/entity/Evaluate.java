package com.handsome.shop.entity;

import com.handsome.shop.framework.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * by wangrongjun on 2017/6/16.
 */
@Entity
@Where(clause = BaseEntity.OBSOLETE_DATE_IS_NULL)
public class Evaluate extends BaseEntity {

    @Transient
    public static final int LEVEL_BAD = 0;
    @Transient
    public static final int LEVEL_NORMAL = 1;
    @Transient
    public static final int LEVEL_GOOD = 2;

    @Id
    @GeneratedValue
    private int evaluateId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordersId")
    private Orders orders;//Order外键，对某次购物（即一份订单）的评价
    private String content;//评价内容
    private int evaluateLevel;//评价，好评，中评，差评
    private String createTime;//评价时间，格式：”yyyy-MM-dd HH:mm:ss”

    @Override
    public String toString() {
        return "Evaluate{" +
                "evaluateId=" + evaluateId +
                ", orders=" + orders +
                ", content='" + content + '\'' +
                ", evaluateLevel=" + evaluateLevel +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public Evaluate() {
    }

    public Evaluate(Orders orders, String content, int evaluateLevel, String createTime) {
        this.orders = orders;
        this.content = content;
        this.evaluateLevel = evaluateLevel;
        this.createTime = createTime;
    }

    public int getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(int evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(int level) {
        this.evaluateLevel = level;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String time) {
        this.createTime = time;
    }
}
