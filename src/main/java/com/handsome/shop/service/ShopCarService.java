package com.handsome.shop.service;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.bean.ShopCar;
import com.handsome.shop.dao.ShopCarDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * by wangrongjun on 2018/4/16.
 */
@Service
public class ShopCarService {

    @Resource
    private ShopCarDao shopCarDao;

    public boolean addGoodsToShopCar(int customerId, int goodsId) {
        return shopCarDao.insert(new ShopCar(new Customer(customerId), new Goods(goodsId)));
    }

}
