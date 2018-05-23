package com.handsome.shop.controller;

import com.handsome.shop.controller.rest.OrdersController;
import com.handsome.shop.controller.rest.ShopController;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.GoodsTypeDao;
import com.handsome.shop.dao.PictureDao;
import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private GoodsDao goodsDao;
    @Resource
    private PictureDao pictureDao;
    @Resource
    private GoodsTypeDao goodsTypeDao;

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
                                  MultipartFile shopHead) throws IOException {
        Shop shop = shopDao.queryById(shopId);
        shop.setShopName(shopName);
        shop.setDescription(description);
        if (!shopHead.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(shopHead.getContentType());
            Blob pictureData = toBlob(shopHead.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            shop.setHead(picture);
        }
        shopDao.update(shop);

        return shop.getHead().getPictureId();
    }

    @GetMapping("/goods/{goodsId}/edit")
    public String showGoodsEditPage(HttpServletRequest request, @PathVariable Integer goodsId) {
        Goods goods = goodsDao.queryById(goodsId);
        String goodsJson = GsonConverter.toJson(goods,
                "Goods.shop", "GoodsAttrName.goods", "GoodsAttrValue.goodsAttrName");

        // TODO 修改时可以修改商品分类
//        List<GoodsType> goodsTypeList = goodsTypeDao.queryAll();
//        String goodsTypeListJson = GsonConverter.toJson(goodsTypeList, "GoodsType.children");

        request.setAttribute("goodsJson", goodsJson);
//        request.setAttribute("goodsTypeListJson", goodsTypeListJson);
        return "seller_goods_edit";
    }

    @PostMapping("/goods/{goodsId}/edit")
    @ResponseBody
    public boolean editGoods(HttpServletRequest request,
                             @PathVariable Integer goodsId,
                             @RequestParam String goodsName,
                             @RequestParam String description,
                             @RequestParam Double price,
                             MultipartFile goodsPicture1,
                             MultipartFile goodsPicture2,
                             MultipartFile goodsPicture3,
                             MultipartFile goodsPicture4,
                             MultipartFile goodsPicture5) throws IOException {
        Goods goods = goodsDao.queryById(goodsId);
        Seller seller = getLoginSellerFromSession(request);
        if (seller == null || !Objects.equals(goods.getShop().getSeller().getSellerId(), seller.getSellerId())) {
            throw new IllegalStateException("You have no access to edit this goods");
        }

        goods.setGoodsName(goodsName);
        goods.setDescription(description);
        goods.setPrice(price);

        Set<Picture> pictureSet = new HashSet<>();
        if (!goodsPicture1.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture1.getContentType());
            Blob pictureData = toBlob(goodsPicture1.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (!goodsPicture2.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture2.getContentType());
            Blob pictureData = toBlob(goodsPicture2.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (!goodsPicture3.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture3.getContentType());
            Blob pictureData = toBlob(goodsPicture3.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (!goodsPicture4.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture4.getContentType());
            Blob pictureData = toBlob(goodsPicture4.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (!goodsPicture5.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture5.getContentType());
            Blob pictureData = toBlob(goodsPicture5.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (pictureSet.size() > 0) {
            goods.setPictureSet(pictureSet);
        }

        goodsDao.update(goods);

        return true;
    }

    @GetMapping("/shop/{shopId}/addGoods")
    public String showGoodsAddPage(HttpServletRequest request, @PathVariable Integer shopId) {
        List<GoodsType> goodsTypeList = goodsTypeDao.queryAll();
        String goodsTypeListJson = GsonConverter.toJson(goodsTypeList, "GoodsType.children");

        request.setAttribute("shopId", shopId);
        request.setAttribute("goodsTypeListJson", goodsTypeListJson);
        return "seller_goods_add";
    }

    @PostMapping("/shop/{shopId}/addGoods")
    @ResponseBody
    public Integer addGoods(HttpServletRequest request,
                            @PathVariable Integer shopId,
                            @RequestParam String goodsName,
                            @RequestParam String description,
                            @RequestParam Double price,
                            @RequestParam Integer goodsTypeId,
                            MultipartFile goodsPicture1,
                            MultipartFile goodsPicture2,
                            MultipartFile goodsPicture3,
                            MultipartFile goodsPicture4,
                            MultipartFile goodsPicture5) throws IOException {// TODO 让图片上传的数量没有上限
        Shop shop = shopDao.queryById(shopId);
        Seller seller = getLoginSellerFromSession(request);
        if (seller == null || !Objects.equals(shop.getSeller().getSellerId(), seller.getSellerId())) {
            throw new IllegalStateException("You have no access to add goods");
        }

        Set<Picture> pictureSet = new HashSet<>();
        if (!goodsPicture1.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture1.getContentType());
            Blob pictureData = toBlob(goodsPicture1.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (!goodsPicture2.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture2.getContentType());
            Blob pictureData = toBlob(goodsPicture2.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (!goodsPicture3.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture3.getContentType());
            Blob pictureData = toBlob(goodsPicture3.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (!goodsPicture4.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture4.getContentType());
            Blob pictureData = toBlob(goodsPicture4.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (!goodsPicture5.isEmpty()) {
            Picture.PictureType pictureType = PictureTypeUtil.toPictureType(goodsPicture5.getContentType());
            Blob pictureData = toBlob(goodsPicture5.getInputStream());
            Picture picture = new Picture(pictureType, pictureData);
            pictureDao.insert(picture);
            pictureSet.add(picture);
        }
        if (pictureSet.size() == 0) {
            throw new IllegalArgumentException("should has at least one picture");
        }

        Goods goods = new Goods(goodsName, description, price, 0, new GoodsType(goodsTypeId), new Shop(shopId), pictureSet);
        goodsDao.insert(goods);

        return goods.getGoodsId();
    }

}
