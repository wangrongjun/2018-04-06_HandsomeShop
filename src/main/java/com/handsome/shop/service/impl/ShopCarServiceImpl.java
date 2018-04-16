package com.handsome.shop.service.impl;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.bean.ShopCar;
import com.handsome.shop.dao.ShopCarDao;
import com.handsome.shop.service.ShopCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * by wangrongjun on 2018/4/16.
 */
@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Resource
    private ShopCarDao shopCarDao;

    @Override
    public boolean addGoodsToShopCar(int customerId, int goodsId) {
        return shopCarDao.insert(new ShopCar(new Customer(customerId), new Goods(goodsId)));
    }

}
