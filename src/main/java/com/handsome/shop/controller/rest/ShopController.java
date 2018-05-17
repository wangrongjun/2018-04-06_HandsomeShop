package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.Shop;
import com.handsome.shop.framework.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@Controller
@RequestMapping("/rest/shop")
public class ShopController extends BaseController {

    @Resource
    private ShopDao shopDao;

    @GetMapping
    public List<Shop> listShop(@RequestParam int sellerId) {
        return shopDao.queryBySellerId(sellerId);
    }

}
