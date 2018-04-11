package com.handsome.shop.action;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Orders;
import com.handsome.shop.framework.DaoFactory;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.framework.ActionSupport;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2017/6/22.
 */
public class QueryCustomerOrderListAction extends ActionSupport {
    @Override
    protected String execute() throws ServletException, IOException, ParamErrorException, CustomerNotExistsException {
        Customer customer = checkCustomer();

        OrdersDao ordersDao = DaoFactory.getOrdersDao();
        List<Orders> ordersList = ordersDao.queryByCustomerId(customer.getCustomerId());
        int ordersCount = ordersDao.queryCountByCustomerId(customer.getCustomerId());

        request.setAttribute("ordersList", ordersList);
        request.setAttribute("ordersCount", ordersCount);
        return "customer_order_list.jsp";
    }
}
