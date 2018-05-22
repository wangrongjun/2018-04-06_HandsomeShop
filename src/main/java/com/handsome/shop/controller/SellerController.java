package com.handsome.shop.controller;

import com.handsome.shop.controller.rest.OrdersController;
import com.handsome.shop.controller.rest.ShopController;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.entity.Goods;
import com.handsome.shop.entity.Orders;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.entity.view.PageParam;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.util.GsonConverter;
import com.handsome.shop.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2018/5/9.
 */
@Controller
public class SellerController extends BaseController {

    @Resource
    private ShopController shopController;
    @Resource
    private OrdersController ordersController;
    @Resource
    private GoodsDao goodsDao;

    @GetMapping("/sellerIndex")
    public String listOwnerShop(HttpServletRequest request) {
        Seller seller = getLoginSellerFromSession(request);
        List<Shop> shopList = shopController.listShop(seller.getSellerId());
        String shopListJson = GsonConverter.toJson(shopList, "seller");

        request.setAttribute("sellerId", seller.getSellerId());
        request.setAttribute("shopListJson", shopListJson);
        return "index_seller";
    }

    @GetMapping("/sellerOrders")
    public String listOrders(HttpServletRequest request) {
        Integer sellerId = getLoginSellerFromSession(request).getSellerId();
        Pager<Orders> pager = ordersController.listBySeller(sellerId, new PageParam("-createdOn"), createBR());
        request.setAttribute("ordersCount", pager.getTotalCount());
        request.setAttribute("ordersListJson", GsonConverter.toJson(pager.getDataList(),
                "Shop.seller", "Refund.orders", "Goods.goodsAttrNames"));
        return "seller_orders_list";
    }

//    @GetMapping("/shop/{shopId}")
//    public String showShopDetail(HttpServletRequest request, @PathVariable int shopId) {
//        int totalCount = goodsDao.queryCountByShopId(shopId);
////        List<Goods> goodsList = goodsDao.queryByShopId(shopId, pageIndex * 12, 12);
//        List<Goods> goodsList = goodsDao.queryByShopId(shopId, 0, 0);
//        Shop shop = shopDao.queryById(shopId);
//
//        request.setAttribute("pageIndex", pageIndex);
//        request.setAttribute("totalCount", totalCount);
//        request.setAttribute("goodsList", goodsList);
//        request.setAttribute("shop", shop);
//        return "shop_detail";
//    }

}
