package com.handsome.shop.controller;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.bean.ShopCar;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.ShopCarDao;
import com.handsome.shop.framework.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@Controller
@RequestMapping("/shopCar")
public class ShopCarController extends BaseController {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private ShopCarDao shopCarDao;

    @GetMapping
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex) {
        Customer customer = getLoginCustomer(request);
        List<Goods> goodsList = goodsDao.queryByCustomerId(customer.getCustomerId(), 12 * pageIndex, 12);
        int totalCount = goodsDao.queryCountByCustomerId(customer.getCustomerId());

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        return "shop_car";
    }

    @PostMapping
    @ResponseBody
    public boolean add(HttpServletRequest request, HttpServletResponse response, int goodsId, int count) throws ServletException, IOException {
        Customer customer = getLoginCustomer(request);
        ShopCar shopCar = new ShopCar(customer, new Goods(goodsId));
        for (int i = 0; i < count; i++) {
            shopCarDao.insert(shopCar);
        }
        return true;
//        request.setAttribute("msg", "已加入购物车");
//        request.getRequestDispatcher("/goods/").forward(request, response);
    }

}
