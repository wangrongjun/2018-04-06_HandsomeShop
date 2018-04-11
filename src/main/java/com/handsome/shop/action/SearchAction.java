package com.handsome.shop.action;

import com.handsome.shop.bean.Goods;
import com.handsome.shop.framework.DaoFactory;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.framework.ActionSupport;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2017/6/21.
 */
public class SearchAction extends ActionSupport {
    @Override
    protected String execute() throws ActionSupport.ParamErrorException, ServletException, IOException {
        String searchWord = checkStringParameter("searchWord");
        int pageIndex = getIntegerParameter("pageIndex", 0);
        int sortType = getIntegerParameter("sortType", 0);

        GoodsDao goodsDao = DaoFactory.getGoodsDao();
        int totalCount = goodsDao.queryCountBySearchWord(searchWord);
        List<Goods> goodsList = goodsDao.queryBySearchWord(searchWord, 12 * pageIndex, 12);

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("sortType", sortType);
        request.setAttribute("searchWord", searchWord);
        return "search_goods.jsp";
    }
}
