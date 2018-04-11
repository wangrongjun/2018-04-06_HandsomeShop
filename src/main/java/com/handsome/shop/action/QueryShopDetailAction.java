package com.handsome.shop.action;

import com.handsome.shop.bean.Goods;
import com.handsome.shop.bean.Shop;
import com.handsome.shop.framework.DaoFactory;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.framework.ActionSupport;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2017/6/22.
 */
public class QueryShopDetailAction extends ActionSupport {
    @Override
    protected String execute() throws ServletException, IOException, ParamErrorException, CustomerNotExistsException {
        int shopId = checkIntegerParameter("shopId");
        int pageIndex = getIntegerParameter("pageIndex", 0);

        GoodsDao goodsDao = DaoFactory.getGoodsDao();
        ShopDao shopDao = DaoFactory.getShopDao();
        int totalCount = goodsDao.queryCountByShopId(shopId);
        List<Goods> goodsList = goodsDao.queryByShopId(shopId, pageIndex * 12, 12);
        Shop shop = shopDao.queryById(shopId);

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("shop", shop);
        return "shop_detail.jsp";
    }
}
