package com.handsome.shop.controller;

import com.handsome.shop.entity.Goods;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.ShopDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@Controller
public class ShopController {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private ShopDao shopDao;

    @RequestMapping("/shop/{shopId}")
    public String showShopDetail(HttpServletRequest request,
                                 @PathVariable int shopId,
                                 @RequestParam(defaultValue = "0") int pageIndex) {
        int totalCount = goodsDao.queryCountByShopId(shopId);
        List<Goods> goodsList = goodsDao.queryByShopId(shopId, pageIndex * 12, 12);
        Shop shop = shopDao.queryById(shopId);

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("shop", shop);
        return "shop_detail";
    }

}
