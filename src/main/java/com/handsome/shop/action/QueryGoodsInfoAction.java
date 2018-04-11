package com.handsome.shop.action;

import com.handsome.shop.bean.Evaluate;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.framework.DaoFactory;
import com.handsome.shop.dao.EvaluateDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.framework.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * by wangrongjun on 2017/6/21.
 */
public class QueryGoodsInfoAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int goodsId;
        try {
            goodsId = Integer.parseInt(request.getParameter("goodsId"));
        } catch (Exception e) {
            e.printStackTrace();
            return "index.jsp";
        }

        GoodsDao goodsDao = DaoFactory.getGoodsDao();
        Goods goods = goodsDao.queryById(goodsId);
        EvaluateDao evaluateDao = DaoFactory.getEvaluateDao();
        List<Evaluate> evaluateList = evaluateDao.queryByGoodsId(goodsId);
        request.setAttribute("goods", goods);
        request.setAttribute("evaluateList", evaluateList);
        return "goods_info.jsp";
    }
}
