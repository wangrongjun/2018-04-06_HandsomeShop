package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopDao shopDao;

    @RequestMapping("/seller/{sellerId}")
    public List<Shop> listShop(@PathVariable int sellerId) {
        return shopDao.queryBySellerId(sellerId);
    }

}
