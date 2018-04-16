package com.handsome.shop.controller;

import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Orders;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.framework.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2018/4/14.
 */
@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @Resource
    private OrdersDao ordersDao;

    @GetMapping
    public String list(HttpServletRequest request) {
        Customer customer = getLoginCustomerFromSession(request);
        List<Orders> ordersList = ordersDao.queryByCustomerId(customer.getCustomerId());
        int ordersCount = ordersDao.queryCountByCustomerId(customer.getCustomerId());

        request.setAttribute("ordersList", ordersList);
        request.setAttribute("ordersCount", ordersCount);
        return "customer_order_list";
    }

}
