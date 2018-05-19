package com.handsome.shop.controller;

import com.handsome.shop.entity.Evaluate;
import com.handsome.shop.entity.Goods;
import com.handsome.shop.dao.EvaluateDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.util.GsonConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * by wangrongjun on 2017/11/2.
 */
@Controller
public class GoodsController extends BaseController {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private EvaluateDao evaluateDao;

    @GetMapping("/searchGoods")
    public String searchGoods(HttpServletRequest request,
                              String searchWord,
                              @RequestParam(defaultValue = "0") int pageIndex,
                              @RequestParam(defaultValue = "0") int sortType)
            throws UnsupportedEncodingException {

        searchWord = URLDecoder.decode(searchWord, "utf-8");

        int totalCount = goodsDao.queryCountBySearchWord(searchWord);
        List<Goods> goodsList = goodsDao.queryBySearchWord(searchWord, 12 * pageIndex, 12);

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("sortType", sortType);
        request.setAttribute("searchWord", searchWord);
        return "search_goods";
    }

    @GetMapping("/goods/{goodsId}")
    public String showGoodsInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer goodsId) {
        Goods goods = goodsDao.queryById(goodsId);
        List<Evaluate> evaluateList = evaluateDao.queryByGoodsId(goodsId);

        request.setAttribute("goodsJson", GsonConverter.toJson(goods,
                "GoodsAttrName.goods", "GoodsAttrValue.goodsAttrName", "Seller.shopList"));
        request.setAttribute("evaluateListJson", GsonConverter.toJson(evaluateList,
                "Orders.goods", "Orders.contact", "Orders.refund"));
        return "goods_info";
    }

    @RequestMapping("/searchGoodsListByType")
    public String searchGoodsListByType(HttpServletRequest request,
                                        int goodsTypeId,
                                        @RequestParam(defaultValue = "0") int pageIndex,
                                        @RequestParam(defaultValue = "0") int sortType) {

        int totalCount = goodsDao.queryCountByGoodsTypeId(goodsTypeId);
        List<Goods> goodsList = goodsDao.queryByGoodsTypeId(goodsTypeId, pageIndex * 12, 12);

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("sortType", sortType);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("goodsTypeId", goodsTypeId);
        return "search_goods_type";
    }

}
