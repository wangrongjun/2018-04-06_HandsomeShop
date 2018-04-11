package com.handsome.shop.action;

import com.handsome.shop.bean.Address;
import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.dao.AddressDao;
import com.handsome.shop.framework.DaoFactory;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.framework.ActionSupport;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2017/6/20.
 */
public class ConfirmOrderAction extends ActionSupport {
    @Override
    protected String execute() throws ServletException, IOException, ParamErrorException, CustomerNotExistsException {
        Customer customer = checkCustomer();
        int goodsId = checkIntegerParameter("goodsId");
        int count = checkIntegerParameter("count");

        GoodsDao goodsDao = DaoFactory.getGoodsDao();
        Goods goods = goodsDao.queryById(goodsId);
        AddressDao addressDao = DaoFactory.getAddressDao();
        List<Address> addressList = addressDao.queryByCustomerId(customer.getCustomerId());

        request.getSession().setAttribute("goods", goods);
        request.getSession().setAttribute("addressList", addressList);
        request.getSession().setAttribute("count", count);
        return "create_order.jsp";
    }
}
