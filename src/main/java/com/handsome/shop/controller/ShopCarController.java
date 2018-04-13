package com.handsome.shop.controller;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@Controller
public class ShopCarController extends BaseController {

    @Autowired
    private GoodsDao goodsDao;

    @RequestMapping("/showShopCar")
    public String showShopCar(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex) {
        Customer customer = getLoginCustomer(request);

        List<Goods> goodsList = goodsDao.queryByCustomerId(
                customer.getCustomerId(), 12 * pageIndex, 12);
        int totalCount = goodsDao.queryCountByCustomerId(customer.getCustomerId());

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        return "shop_car";
    }

}
