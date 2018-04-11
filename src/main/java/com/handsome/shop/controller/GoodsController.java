package com.handsome.shop.controller;

import com.handsome.shop.bean.Evaluate;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.dao.EvaluateDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.framework.DaoFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2017/11/2.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @GetMapping("/{goodsId}")
    public String showGoodsInfo(HttpServletRequest request, @PathVariable Integer goodsId) {
        GoodsDao goodsDao = DaoFactory.getGoodsDao();
        Goods goods = goodsDao.queryById(goodsId);
        EvaluateDao evaluateDao = DaoFactory.getEvaluateDao();
        List<Evaluate> evaluateList = evaluateDao.queryByGoodsId(goodsId);
        request.setAttribute("goods", goods);
        request.setAttribute("evaluateList", evaluateList);
        return "goods_info";
    }

}
