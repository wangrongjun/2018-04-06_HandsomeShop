package com.handsome.shop.controller;

import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.GoodsTypeDao;
import com.handsome.shop.entity.Goods;
import com.handsome.shop.entity.GoodsType;
import com.handsome.shop.framework.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2017/11/2.
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private GoodsTypeDao goodsTypeDao;

    @RequestMapping("/")
    public String showIndex(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "0") int sortType) throws ServletException, IOException {

        if (getLoginSellerFromSession(request) != null) {
            request.getRequestDispatcher("/sellerIndex").forward(request, response);
            return null;
        }

        List<GoodsType> goodsTypeList = goodsTypeDao.queryAll();
        List<Goods> goodsList = goodsDao.begin(12 * pageIndex).count(12).sortType(sortType).queryAll();
        long totalCount = goodsDao.queryCount(null);

        request.getSession().setAttribute("goodsTypeList", goodsTypeList);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("sortType", sortType);
        return "index";
    }

    @RequestMapping("/test")
    public String test(@RequestParam Integer testId) {
        System.out.println(testId);
        return "test";
    }

}
