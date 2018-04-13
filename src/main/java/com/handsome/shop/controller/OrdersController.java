package com.handsome.shop.controller;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Orders;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.framework.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@Controller
public class OrdersController extends BaseController {

    @Autowired
    private OrdersDao ordersDao;

    @RequestMapping("/showOrders")
    public String showOrders(HttpServletRequest request) {
        Customer customer = getLoginCustomer(request);

        OrdersDao ordersDao = DaoFactory.getOrdersDao();
        List<Orders> ordersList = ordersDao.queryByCustomerId(customer.getCustomerId());
        int ordersCount = ordersDao.queryCountByCustomerId(customer.getCustomerId());

        request.setAttribute("ordersList", ordersList);
        request.setAttribute("ordersCount", ordersCount);
        return "customer_order_list";
    }

}
