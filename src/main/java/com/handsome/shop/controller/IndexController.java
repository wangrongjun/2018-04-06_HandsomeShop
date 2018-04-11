package com.handsome.shop.controller;

import com.handsome.shop.bean.Goods;
import com.handsome.shop.bean.GoodsType;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.GoodsTypeDao;
import com.handsome.shop.framework.ActionSupport;
import com.handsome.shop.framework.DaoFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2017/11/2.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndex(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "0") int sortType) {
        GoodsTypeDao goodsTypeDao = DaoFactory.getGoodsTypeDao();
        List<GoodsType> goodsTypeList = goodsTypeDao.queryAll();
        GoodsDao goodsDao = DaoFactory.getGoodsDao();
        List<Goods> goodsList = goodsDao.begin(12 * pageIndex).count(12).sortType(sortType).queryAll();
        long totalCount = goodsDao.queryCount(null);

        request.getSession().setAttribute("goodsTypeList", goodsTypeList);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("sortType", sortType);
        return "index";
    }
}
