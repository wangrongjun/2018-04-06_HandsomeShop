package com.handsome.shop.controller;

import com.handsome.shop.controller.rest.ShopController;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.util.GsonConverter;
import com.handsome.shop.util.RequestStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/sellerIndex")
    public String listOwnerShop(HttpServletRequest request) {
        Seller seller = getLoginSellerFromSession(request);
        List<Shop> shopList = shopController.listShop(seller.getSellerId());
        String shopListJson = GsonConverter.toJson(shopList, "seller");

        request.setAttribute("sellerId", seller.getSellerId());
        request.setAttribute("shopListJson", shopListJson);
        return "index_seller";
    }

    @PostMapping("/createShop")
    public RequestStatus add(@RequestParam String shopName,
                             @RequestParam String shopDescription) {
        return RequestStatus.success();
    }

}
