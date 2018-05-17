package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.PictureDao;
import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.Picture;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.framework.ReturnObjectToJsonIgnoreFields;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
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
    public List<Shop> listShop(@RequestParam Integer sellerId) {
        return shopDao.queryBySellerId(sellerId);
    }

    @PostMapping
    public Shop add(HttpServletRequest request,
                    @RequestParam Integer sellerId,
                    @RequestParam String shopName,
                    @RequestParam String shopDescription) throws IOException {
        String picturePath = request.getServletContext().getRealPath("/admin/img/shop_default_head.jpg");
        FileInputStream fis = new FileInputStream(picturePath);
        Blob pictureData = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(fis, fis.available());
        Picture picture = new Picture(Picture.PictureType.jpg, pictureData);
        pictureDao.insert(picture);
        Shop shop = new Shop(new Seller(sellerId), shopName, shopDescription, picture);
        shopDao.insert(shop);
        return shop;
    }

}
