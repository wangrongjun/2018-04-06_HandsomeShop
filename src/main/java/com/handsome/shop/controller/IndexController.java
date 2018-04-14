package com.handsome.shop.controller;

import com.handsome.shop.bean.Goods;
import com.handsome.shop.bean.GoodsType;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.GoodsTypeDao;
import com.handsome.shop.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2017/11/2.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @RequestMapping("/")
    public String showIndex(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "0") int sortType) {
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

}
