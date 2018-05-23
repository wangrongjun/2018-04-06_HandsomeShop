package com.handsome.shop.controller;

import com.handsome.shop.controller.rest.OrdersController;
import com.handsome.shop.controller.rest.ShopController;
import com.handsome.shop.dao.PictureDao;
import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.Orders;
import com.handsome.shop.entity.Picture;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.entity.view.PageParam;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.util.GsonConverter;
import com.handsome.shop.util.Pager;
import com.handsome.shop.util.PictureTypeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

/**
 * by wangrongjun on 2018/5/9.
 */
@Controller
@RequestMapping("/seller")
public class SellerController extends BaseController {

    @Resource
    private ShopController shopController;
    @Resource
    private OrdersController ordersController;
    @Resource
    private ShopDao shopDao;
    @Resource
    private PictureDao pictureDao;

    @GetMapping
    public String sellerIndex(HttpServletRequest request) {
        Seller seller = getLoginSellerFromSession(request);
        List<Shop> shopList = shopController.list(seller.getSellerId());
        String shopListJson = GsonConverter.toJson(shopList, "Shop.seller", "Shop.goodsSet");

        request.setAttribute("sellerId", seller.getSellerId());
        request.setAttribute("shopListJson", shopListJson);
        return "index_seller";
    }

    @GetMapping("/orders")
    public String listOrders(HttpServletRequest request) {
        Integer sellerId = getLoginSellerFromSession(request).getSellerId();
        Pager<Orders> pager = ordersController.listBySeller(sellerId, new PageParam("-createdOn"), createBR());
        request.setAttribute("ordersCount", pager.getTotalCount());
        request.setAttribute("ordersListJson", GsonConverter.toJson(pager.getDataList(),
                "Shop.seller", "Refund.orders", "Goods.goodsAttrNames"));
        return "seller_orders_list";
    }

    @GetMapping("/shop/{shopId}")
    public String showShopDetail(HttpServletRequest request, @PathVariable int shopId) {
        Shop shop = shopDao.queryById(shopId);
        String shopJson = GsonConverter.toJson(shop, "Shop.seller", "Goods.shop", "Goods.goodsAttrNames");

        request.setAttribute("editable", true);
        request.setAttribute("shopJson", shopJson);
        return "shop_detail";
    }

    @PostMapping("/shop/{shopId}/updateShopInfo") // 原本应该是PUT，但由于参数包含图片，前端只能使用POST，导致这里也是POST
    @ResponseBody
    public Integer updateShopInfo(@PathVariable int shopId,
                                  @RequestParam String shopName,
                                  @RequestParam String description,
                                  MultipartFile shopHead) {
        boolean empty = shopHead.isEmpty();
        String name = shopHead.getName();
        String originalFilename = shopHead.getOriginalFilename();
        String contentType = shopHead.getContentType();

        Shop shop = shopDao.queryById(shopId);
        shop.setShopName(shopName);
        shop.setDescription(description);
        shopDao.update(shop);
        return shop.getHead().getPictureId();
    }

    @PostMapping("/shop/{shopId}/updateHead")
    @ResponseBody
    public Integer updateShopHead(@PathVariable int shopId,
                                  @RequestParam MultipartFile headFile) throws IOException {
        Picture.PictureType pictureType = PictureTypeUtil.toPictureType(headFile.getContentType());
        Blob pictureData = toBlob(headFile.getInputStream());
        Picture head = new Picture(pictureType, pictureData);
        pictureDao.insert(head);

        Shop shop = shopDao.queryById(shopId);
        shop.setHead(head);
        shopDao.update(shop);

        return head.getPictureId();
    }

}
