package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.PictureDao;
import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.Picture;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.framework.ReturnObjectToJsonIgnoreFields;
import com.handsome.shop.util.PictureTypeUtil;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@Controller
@RequestMapping("/rest/shop")
public class ShopController extends BaseController {

    @Resource
    private ShopDao shopDao;
    @Resource
    private PictureDao pictureDao;
    @Resource
    private SessionFactory sessionFactory;// TODO 想办法把这个东西移走

    @GetMapping
    public List<Shop> list(@RequestParam Integer sellerId) {
        return shopDao.queryBySellerId(sellerId);
    }

    @GetMapping("/{shopId}")
    @ReturnObjectToJsonIgnoreFields("")
    public Shop get(@PathVariable int shopId) {
        return shopDao.queryById(shopId);
    }

    @PostMapping
    public Shop add(@RequestParam Integer sellerId,
                    @RequestParam String shopName,
                    @RequestParam String shopDescription,
                    @RequestParam MultipartFile shopHead) throws IOException {
        if (shopHead.isEmpty()) {
            throw new IllegalArgumentException("shopHead is empty");
        }
        Picture.PictureType pictureType = PictureTypeUtil.toPictureType(shopHead.getContentType());
        Blob pictureData = toBlob(shopHead.getInputStream());
        Picture picture = new Picture(pictureType, pictureData);
        pictureDao.insert(picture);

        Shop shop = new Shop(new Seller(sellerId), shopName, shopDescription, picture);
        shopDao.insert(shop);
        return shop;
    }

}
