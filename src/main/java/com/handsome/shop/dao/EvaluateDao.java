package com.handsome.shop.dao;

import com.handsome.shop.entity.Evaluate;
import com.handsome.shop.framework.Dao;

import java.util.List;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface EvaluateDao extends Dao<Evaluate, Integer> {

    /**
     * 查询某个商品下的所有评价（通过订单表连接）
     */
    List<Evaluate> queryByGoodsId(int goodsId);

//    List<Evaluate> queryByCustomerId(int customerId);//查询某个客户的所有评价（通过订单表连接）

}
