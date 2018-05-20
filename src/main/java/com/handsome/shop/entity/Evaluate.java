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

    public enum Level {
        Good(0),// 好评
        Normal(1),// 中评
        Bad(2);// 差评

        int code;

        Level(int code) {
            this.code = code;
        }
    }

    @Id
    @GeneratedValue
    private Integer evaluateId;
    @ManyToOne
    @JoinColumn(name = "ordersId")
    private Orders orders;// Order外键，对某次购物（即一份订单）的评价
    private String content;// 评价内容
    @Enumerated(EnumType.STRING)
    private Level level;// 评价等级

    @Override
    public String toString() {
        return "Evaluate{" +
                "evaluateId=" + evaluateId +
                ", orders=" + orders +
                ", content='" + content + '\'' +
                ", level=" + level +
                '}';
    }

    public Evaluate() {
    }

    public Evaluate(Orders orders, String content, Level level) {
        this.orders = orders;
        this.content = content;
        this.level = level;
    }

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
