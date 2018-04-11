package com.handsome.shop.action;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.bean.ShopCar;
import com.handsome.shop.framework.DaoFactory;
import com.handsome.shop.dao.ShopCarDao;
import com.handsome.shop.framework.ActionSupport;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * by wangrongjun on 2017/6/20.
 */
public class AddGoodsToShopCarAction extends ActionSupport {
    @Override
    protected String execute() throws ServletException, IOException, ParamErrorException, CustomerNotExistsException {
        Customer customer = checkCustomer();
        int goodsId = checkIntegerParameter("goodsId");
        int count = checkIntegerParameter("count");

        ShopCarDao shopCarDao = DaoFactory.getShopCarDao();
        ShopCar shopCar = new ShopCar(customer, new Goods(goodsId));
        for (int i = 0; i < count; i++) {
            shopCarDao.insert(shopCar);
        }

        request.setAttribute("msg", "已加入购物车");
        return "queryGoodsInfo.do";
    }
}
