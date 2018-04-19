package com.handsome.shop.controller;

import com.handsome.shop.bean.Address;
import com.handsome.shop.bean.Customer;
import com.handsome.shop.bean.Goods;
import com.handsome.shop.bean.Orders;
import com.handsome.shop.dao.AddressDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.framework.BaseController;
import com.wangrj.java_lib.java_util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private AddressDao addressDao;

    @GetMapping
    public String list(HttpServletRequest request) {
        Customer customer = getLoginCustomerFromSession(request);
        List<Orders> ordersList = ordersDao.queryByCustomerId(customer.getCustomerId());
        int ordersCount = ordersDao.queryCountByCustomerId(customer.getCustomerId());

        request.setAttribute("ordersList", ordersList);
        request.setAttribute("ordersCount", ordersCount);
        return "customer_order_list";
    }

    @PostMapping("/confirm")
    public String confirm(HttpServletRequest request, int goodsId, int count) {
        Customer customer = getLoginCustomerFromSession(request);
        Goods goods = goodsDao.queryById(goodsId);
        List<Address> addressList = addressDao.queryByCustomerId(customer.getCustomerId());

        request.getSession().setAttribute("goods", goods);
        request.getSession().setAttribute("addressList", addressList);
        request.getSession().setAttribute("count", count);// TODO 应该不需要放到session中
        return "create_order";
    }

    @PostMapping
    @ResponseBody
    public boolean create(HttpServletRequest request, String address, String phone, String receiverName) {
        // TODO 把参数保存到Contact对象中
        Customer customer = getLoginCustomerFromSession(request);
        Goods goods = (Goods) request.getSession().getAttribute("goods");
        if (goods == null) {
            return false;
        }
        int count;
        try {
            count = (int) request.getSession().getAttribute("count");
        } catch (Exception e) {
            return false;
        }

        Orders orders = new Orders(new Customer(customer.getCustomerId()), goods, count, count * goods.getPrice(),
                phone, receiverName, address, DateUtil.getCurrentDateAndTime(), Orders.STATE_CONTINUE);
        ordersDao.insert(orders);

        // TODO 成功添加订单后，address_list,goods,count等变量就没必要了，可以清除
        request.getSession().removeAttribute("address_list");
        request.getSession().removeAttribute("goods");
        request.getSession().removeAttribute("count");

        request.getSession().setAttribute("orders", orders);
        return true;
    }

}
